package com.djhonj.automessage.usecases

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.djhonj.automessage.domain.DateTime
import com.djhonj.automessage.domain.InformationSend
import com.djhonj.automessage.framework.data.AlertReceiver
import com.djhonj.automessage.framework.data.MyService
import kotlin.random.Random

class ProgramarEnvio(
    private val context: Context,
    private val alarmManager: AlarmManager
) {
    private val REQUEST_CODE: Int = 100

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun invoke(dateTimeMillisecond: Long, dateTimeInfo: DateTime, informationSend: InformationSend): Boolean {
        //alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //creamos en pending intent para enviarselo al broadcastreceiver
        val intent = Intent(context, AlertReceiver::class.java).apply {
            putExtra("informationsend", informationSend)
        }

        //flag: cancela el anterior y crea uno nuevo
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, Random.nextInt(0, 1000), intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeMillisecond, pendingIntent)

        //NotificationApp(context).sendForeground()

        ContextCompat.startForegroundService(context, Intent(context, MyService::class.java).apply {
            putExtra("datetime", dateTimeInfo)
        })

        return true
    }
}