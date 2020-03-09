package `in`.subha.medtracker.service

import `in`.subha.medtracker.R
import `in`.subha.medtracker.mainactivity.MainActivity2
import android.app.*
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import kotlin.random.Random

@Suppress("DEPRECATION")
class Service : Service() {
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "`in`.subha.medtracker"
    private val description = "Test notification"
    override fun onCreate() {
        super.onCreate()
        addnotification()
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }
    fun addnotification(){
        val intent = Intent(this,MainActivity2::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val contentView = RemoteViews(packageName,
            R.layout.activity_main_2)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.CYAN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }else{

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        val random=Random
        val id=random.nextInt(1,10)
        notificationManager.notify(id,builder.build())
    }
}
