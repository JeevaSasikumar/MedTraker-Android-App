package `in`.subha.medtracker.notification
import `in`.subha.medtracker.R
import `in`.subha.medtracker.mainactivity.MainActivity2
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class Notify:AppCompatActivity() {
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "in.subha.medtraker.notification"
    private val description = "Test notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this,MainActivity2::class.java)

        val pendingIntent = PendingIntent.getActivity(this,
            0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.images2)
                .setContentTitle("Tablet Remainder")
                .setContentText("Take Your Tablets Please!")
                .setContentIntent(pendingIntent)
        }else{

            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.images2)
                .setContentTitle("Tablet Remainder")
                .setContentText("Take Your Tablets Please!")
                .setContentIntent(pendingIntent)
        }
        val r=(0..100).random()
        notificationManager.notify(r,builder.build())
    }
}