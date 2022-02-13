package com.automessage.framework

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.legacy.content.WakefulBroadcastReceiver.completeWakefulIntent

class MyIntentService: IntentService("MuIntentService") {
    private val notificationManager: NotificationManager? = null
    internal var builder: NotificationCompat.Builder? = null

    override fun onHandleIntent(intent: Intent?) {
        //val extras: Bundle? = intent?.extras

        // Do the work that requires your app to keep the CPU running.
        // ...
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        //WakeReceiver.completeWakefulIntent(intent,)
    }
}