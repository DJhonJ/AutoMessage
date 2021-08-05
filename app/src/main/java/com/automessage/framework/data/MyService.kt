package com.automessage.framework.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.automessage.domain.DateTime
import java.io.Serializable

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