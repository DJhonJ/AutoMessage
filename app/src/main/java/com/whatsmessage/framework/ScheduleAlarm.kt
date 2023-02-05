package com.whatsmessage.framework

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.whatsmessage.data.datasource.ILocalMessage
import com.whatsmessage.domain.DateTime
import com.whatsmessage.domain.Message
import kotlin.random.Random

class ScheduleAlarm(private val contextApp: Context) {
    @RequiresApi(Build.VERSION_CODES.KITKAT) //se ejecuta desde la version API KITKAT en adelante
    fun schedule(message: Message): Boolean {
        try {
            val alarmMgr: AlarmManager? = contextApp.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent(contextApp, AlarmReceiver::class.java).apply {
                putExtra("messageSend", message)
            }

            val alarmPendingIntent: PendingIntent = intent.let {
                PendingIntent.getBroadcast(contextApp, message.id_message, it, PendingIntent.FLAG_IMMUTABLE)
            }

            alarmMgr?.setExact(
                AlarmManager.RTC_WAKEUP,
                message.dateTimeMillisecond,
                alarmPendingIntent
            )
        } catch (e: Exception) {
            Log.i("error", e.message.toString())
            return false
        }

        return true
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