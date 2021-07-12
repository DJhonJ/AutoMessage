package com.djhonj.automessage.usecases

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.djhonj.automessage.framework.data.AlertReceiver

class ProgramarEnvio(
    private val context: Context,
    private val alarmManager: AlarmManager
) {
    private val REQUEST_CODE: Int = 100

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun invoke(dateTimeMillisecond: Long): Boolean {
        //alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //creamos en pending intent para enviarselo al broadcastreceiver
        val pendingIntent = Intent(context, AlertReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeMillisecond, pendingIntent)

        return true
    }
}