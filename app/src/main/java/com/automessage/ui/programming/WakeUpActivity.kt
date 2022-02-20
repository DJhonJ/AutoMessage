package com.automessage.ui.programming

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.speech.tts.TextToSpeech
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.automessage.R
import com.automessage.domain.Message
import com.automessage.ui.common.Constants
import org.koin.core.context.unloadKoinModules

class WakeUpActivity : AppCompatActivity() {
    private lateinit var fullWakeLock: PowerManager.WakeLock
    private lateinit var partialWakeLock: PowerManager.WakeLock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up)
        //launchActivity()
        wakeUpPowerManager()
        //unLock()
        //send()
    }

    override fun onResume() {
        super.onResume()
        //unLock()

//        if(fullWakeLock.isHeld){
//            fullWakeLock.release();
//        }
//
//        if(partialWakeLock.isHeld){
//            partialWakeLock.release();
//        }
    }

    override fun onPause() {
        super.onPause()
        //partialWakeLock.acquire()
    }

    private fun launchActivity() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //send()
    }

    @SuppressLint("InvalidWakeLockTag")
    private fun wakeUpPowerManager() {
        val powerManager = this.getSystemService(Context.POWER_SERVICE) as PowerManager?
        powerManager?.let {
            fullWakeLock = it.newWakeLock((PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP),"full")
            //partialWakeLock = it.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"partial")
            fullWakeLock.acquire()
            //fullWakeLock.release()
        }

        //unLock()
    }

    private fun unLock() {
        //fullWakeLock.acquire()
        send()
        //val km = this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        //val kl = km.newKeyguardLock("name")
        //kl.disableKeyguard()
    }

    private fun send() {
        var isScreenOn = false

        val powerManager = this.getSystemService(Context.POWER_SERVICE) as PowerManager?
        powerManager?.let { pm ->
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                isScreenOn = pm.isInteractive
            } else {
                isScreenOn = pm.isScreenOn
            }
        }

        if (isScreenOn) {
            val message = intent.extras?.getSerializable("messageSend") as Message?

                //envio de message
//            message?.let { msg ->
//                val _intent = Intent().apply {
//                    action = Intent.ACTION_VIEW
//                    `package` = Constants.ID_WHATSAPP
//                    data = Uri.parse("https://api.whatsapp.com/send?text=${msg.message}&phone=${msg.phones}")
//                    //flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                }
//
//                this.startActivity(_intent)
//            }
        }
    }
}