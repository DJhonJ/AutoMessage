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

    private val REQUEST_CODE: Int = 100

    //TODO el request_code (pendingIntent) debe de ser unico por cada envio, para que no de problemas al enviarlos

    fun invoke(dateTimeMillisecond: Long, dateTimeInfo: DateTime, phone: String?, message: String): Boolean {
        val context: Context? = null
        val alarmManager: AlarmManager? = null

        /*alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //creamos en pending intent para enviarselo al broadcastreceiver
        val intent = Intent(context, AlertReceiver::class.java).apply {
            putExtra("informationsend", informationSend)
        }

        //flag: cancela el anterior y crea uno nuevo
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, Random.nextInt(0, 1000),
            intent, PendingIntent.FLAG_IMMUTABLE)

        if (Build.VERSION_CODES.KITKAT > Build.VERSION.SDK_INT) {
            //Otro metedo
        }
        else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeMillisecond, pendingIntent)
        }

        //NotificationApp(context).sendForeground()

        ContextCompat.startForegroundService(context, Intent(context, MyService::class.java).apply {
            putExtra("datetime", dateTimeInfo)
        })*/

        return true
    }
}