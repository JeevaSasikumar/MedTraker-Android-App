package `in`.subha.medtracker.alarmmanager

import `in`.subha.medtracker.notification.Notify
import `in`.subha.medtracker.service.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReciever:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("samplecall","AlarmReciever called")
        val intent=Intent(context,Service::class.java)
        context.startService(intent)
        Toast.makeText(context,"take tablets",Toast.LENGTH_SHORT).show()
    }
}
