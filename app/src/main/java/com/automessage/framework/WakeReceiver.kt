package com.automessage.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.legacy.content.WakefulBroadcastReceiver
import androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService
import com.automessage.ui.main.MainActivity

class WakeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //startWakefulService(context, Intent(context, MainActivity::class.java))

        Toast.makeText(context, "ALARMA", Toast.LENGTH_SHORT).show()
    }
}