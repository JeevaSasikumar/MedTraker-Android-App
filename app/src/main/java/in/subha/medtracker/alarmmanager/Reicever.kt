package `in`.subha.medtracker.alarmmanager

import `in`.subha.medtracker.mainactivity.MainActivity2
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Reicever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("Alarm","Started receiver")

        val intent=Intent(context,
            MainActivity2::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        Log.d("Alarm","next class called")

    }
}
