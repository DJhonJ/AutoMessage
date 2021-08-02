package com.djhonj.automessage.framework.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationApp(this).createNotification("nuevo envio", "el dia xx se enviara su mensaje",
            NotificationCompat.PRIORITY_LOW)

        startForeground(NotificationApp.ID_NOTIFICACION_FOREGROUND, notification)

        return START_NOT_STICKY
    }
}