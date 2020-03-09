package `in`.subha.medtracker.mainactivity

import `in`.subha.medtracker.alarmmanager.AlarmReciever
import `in`.subha.medtracker.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_2.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity2:AppCompatActivity() {
    lateinit var context:Context
    lateinit var alarmManager: AlarmManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)
        context=this
        alarmManager=context.getSystemService(Context.ALARM_SERVICE)as AlarmManager
        Log.d("Alarm", "called")
        var tabletlist = ArrayList<String>()

        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            tabletlist
        )
        btn_add.setOnClickListener {

            if (tablet_text.text.toString() == "") {
                Toast.makeText(this, "Please Provide Tablet Name", Toast.LENGTH_SHORT).show()
            } else if (hour_text.text.toString() == "" || minute_text.text.toString() == "") {
                Toast.makeText(this, "Please Provide Correct Time Format", Toast.LENGTH_SHORT)
                    .show()
            } else if (hour_text.text.toString().toInt() > 12 || hour_text.text.toString().toInt() < 0) {
                Toast.makeText(this, "Enter Correct Hour", Toast.LENGTH_SHORT).show()
            } else if (minute_text.text.toString().toInt() > 59 || minute_text.text.toString().toInt() < 0) {
                Toast.makeText(this, "Enter Correct Minute", Toast.LENGTH_SHORT).show()
            }

            else {
                if (hour_text.text.length == 1) {
                    hour_text.setText("0" + hour_text.text.toString())
                }
                if (minute_text.text.length == 1) {
                    minute_text.setText("0" + minute_text.text.toString())
                }
                Toast.makeText(this, "Alarm Created", Toast.LENGTH_SHORT).show()
                tabletlist.add(tablet_text.text.toString() + "\n@ " + hour_text.text.toString() + ":" + minute_text.text.toString() + btn_am_pm.text.toString())
                list_view.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            val intent=Intent(context,AlarmReciever::class.java)
            val pendingIntent=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            val cal= Calendar.getInstance().apply {
                timeInMillis=System.currentTimeMillis()
                set(Calendar.HOUR,hour_text.text.toString().toInt())
                set(Calendar.MINUTE,minute_text.text.toString().toInt())
                if(btn_am_pm.text.toString()=="PM"){
                    set(Calendar.AM_PM,Calendar.PM)
                }else{
                    set(Calendar.AM_PM,Calendar.AM)
                }
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)
        }


        btn_delete.setOnClickListener {
            val position: SparseBooleanArray = list_view.checkedItemPositions
            val count = list_view.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(tabletlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Selected Reminders Are Deleted", Toast.LENGTH_SHORT).show()

        }
        btn_am_pm.setOnClickListener {
            if(btn_am_pm.text.toString()=="AM"){
                btn_am_pm.text="PM"
            }
            else{
                btn_am_pm.text="AM"
            }
        }
    }
}
