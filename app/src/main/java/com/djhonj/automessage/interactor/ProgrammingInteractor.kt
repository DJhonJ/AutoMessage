package com.djhonj.automessage.interactor

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.djhonj.automessage.interfaces.IProgrammingInteractor
import com.djhonj.automessage.interfaces.IProgrammingPresenter
import com.djhonj.automessage.interfaces.IShowMessage
import com.djhonj.automessage.receiver.AlertReceiver
import java.util.*

class ProgrammingInteractor(private val presenter: IProgrammingPresenter): IProgrammingInteractor {
    private lateinit var alarmManager: AlarmManager
    private val REQUEST_CODE: Int = 100

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun save(activity: Activity, dateTimeMillisecond: Long) {
        alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //creamos en pending intent para enviarselo al broadcastreceiver
        val pendingIntent = Intent(activity, AlertReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(activity, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeMillisecond, pendingIntent)

        presenter.show("save")
    }

    override fun show(message: String) {
        presenter.show(message)
    }
}