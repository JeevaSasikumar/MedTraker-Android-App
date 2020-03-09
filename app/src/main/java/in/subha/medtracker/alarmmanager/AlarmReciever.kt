package `in`.subha.medtracker.alarmmanager

import `in`.subha.medtracker.service.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReciever:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intent=Intent(context,Service::class.java)
        context.startService(intent)
    }
}
