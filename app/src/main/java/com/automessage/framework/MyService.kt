package com.automessage.framework

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.automessage.domain.DateTime
import kotlin.random.Random

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.extras?.getSerializable("datetime") == null) {
            return START_NOT_STICKY
        }

        val dateTimeInfo = intent?.extras?.getSerializable("datetime") as DateTime?

        val notification = NotificationApp(this).createNotification("nuevo envio",
            "el dia ${dateTimeInfo!!.date} a las ${dateTimeInfo!!.time} se enviara su mensaje",
            NotificationCompat.PRIORITY_LOW)

        startForeground(NotificationApp.ID_NOTIFICACION_FOREGROUND, notification)

        return START_NOT_STICKY
    }
}