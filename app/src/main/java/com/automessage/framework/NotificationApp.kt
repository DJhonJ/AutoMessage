package com.automessage.framework

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.automessage.R

class NotificationApp(private val context: Context) {
    companion object {
        val CHANNEL_ID = "automessage_notification"
        val ID_NOTIFICATION = 1
        val ID_NOTIFICACION_FOREGROUND = 2
    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "AutoMessage_Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val serviceChannel = NotificationChannel(CHANNEL_ID, name, importance)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    fun send(title: String = "title", description: String = "description") {
        val notification = createNotification(title, description)

        NotificationManagerCompat.from(context).notify(ID_NOTIFICATION, notification)
    }

    fun createNotification(title: String, description: String, priority: Int = NotificationCompat.PRIORITY_DEFAULT): Notification {
        //context, MainActivity::class.java -> iban en constructor intent
        val pendingIntent: PendingIntent = Intent().let {
            PendingIntent.getActivity(context, 0, it, 0)
        }

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_automessage)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }
}