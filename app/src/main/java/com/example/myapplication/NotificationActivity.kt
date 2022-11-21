package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder

class NotificationActivity : AppCompatActivity() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"

    val NOTIFICATION_ID=0

    val btnNotification by lazy {
        findViewById<Button>(R.id.btn_notificaton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        createNotificationChannel()

        val Intent= Intent(this,NotificationActivity::class.java )

        val pendingIntent=TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Awesome notification")
            .setContentText("This is the context text")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val notificationManager=NotificationManagerCompat.from(this)

        btnNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
        }

    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel=NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor= Color.GREEN
                enableLights(true)
            }// channel create

            val manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)


        }
    }

}