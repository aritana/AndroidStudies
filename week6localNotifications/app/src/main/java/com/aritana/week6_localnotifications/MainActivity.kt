package com.aritana.week6_localnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        var channel = NotificationChannel("1", "Notification",IMPORTANCE_HIGH)
            .apply {
                setSound(buildBounceSoundUri(),audioAttributes)

            }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)


        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            dispatcherNotification()
        }


    }

    private fun dispatcherNotification() {

        val firstIntent = Intent(this, SecondActivity::class.java).apply {

            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("NOTIFICATION_ACTION_EXTRA_ID", "NOFTIFICATION_ACTION_A")
        }

        val pendingFirstIntent:PendingIntent = PendingIntent.getActivity(this,1,firstIntent,FLAG_UPDATE_CURRENT)

        val secondIntent=Intent(this,SecondActivity::class.java).apply {

            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("NOTIFICATION_ACTION_EXTRA_ID", "NOFTIFICATION_ACTION_A")
        }

        val pendingSecondIntent:PendingIntent = PendingIntent.getActivity(this,2,secondIntent,FLAG_UPDATE_CURRENT)





        var resource = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)


        val builder = NotificationCompat.Builder(this, "1")
            .setContentTitle("Notification Title")
            .setContentText("Lorem Ipsum dolor")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(resource)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(resource).bigLargeIcon(null))
            .addAction(

                NotificationCompat.Action.Builder(null, "NOTIFICATION_ACTION_A",pendingFirstIntent).build()
            )
            .addAction(

                NotificationCompat.Action.Builder(null, "NOTIFICATION_ACTION_B",pendingSecondIntent).build()
            )


        val notification = builder.build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(nextInt(), notification)
    }

  private fun buildBounceSoundUri() = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.mp3)
}