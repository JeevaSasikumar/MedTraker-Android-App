package `in`.subha.medtracker.mainactivity

import `in`.subha.medtracker.R
import `in`.subha.medtracker.alarmmanager.Reicever
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    lateinit var context:Context
    lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
        alarmManager=context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent=  Intent(context, Reicever::class.java)
        val pendingintent=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+2000,pendingintent)
        Log.d("Alarm","Start receiver")
    }
}
