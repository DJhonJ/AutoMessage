package com.whatsmessage.ui.programming

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
import android.text.Layout
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.whatsmessage.R
import com.whatsmessage.databinding.ActivityProgrammingBinding
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message
import com.whatsmessage.ui.common.Constants
import com.whatsmessage.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.context.unloadKoinModules

class WakeUpActivity : AppCompatActivity() {
    private lateinit var fullWakeLock: PowerManager.WakeLock
    private lateinit var partialWakeLock: PowerManager.WakeLock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up)
        //launchActivity()
        //
        lifecycleScope.launch {
            wakeUpPowerManager()

            delay(4000)

            //unLock()

            //delay(1000)

            launchActivity()
        }

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

    private suspend fun launchActivity() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)

        val message = intent.extras?.getSerializable("messageSend") as Message?
        message?.let {
            val l = findViewById<LinearLayout>(R.id.layout_wakeup)
            val phones  = arrayListOf<String>()

            for (contact: Contact in message.contacts) {
                l.addView(TextView(this).apply {
                    text = "${contact.name} ${contact.number}"
                })

                phones.add("phone=${contact.number}")
            }

            val _intent = Intent().apply {
                action = Intent.ACTION_VIEW
                `package` = Constants.ID_WHATSAPP
                data = Uri.parse("https://api.whatsapp.com/send?text=${message.message}&${phones.joinToString("&")}")
                //flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            delay(1000)

            //startActivity(Intent(this, MainActivity::class.java))

            this.startActivity(_intent)
            finish()
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    private fun wakeUpPowerManager() {
        val powerManager = this.getSystemService(Context.POWER_SERVICE) as PowerManager?
        powerManager?.let {
            fullWakeLock = it.newWakeLock((PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP),"full")
            //partialWakeLock = it.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"partial")
            fullWakeLock.acquire()

        }

        //unLock()
    }

    private fun unLock() {
        //fullWakeLock.acquire()

        val km = this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        val kl = km.newKeyguardLock("name")
        kl.disableKeyguard()

        fullWakeLock.release()
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

            message?.let {
                val l = findViewById<LinearLayout>(R.id.layout_wakeup)

                for (contact: Contact in message.contacts) {
                    l.addView(TextView(this).apply {
                        text = "${contact.name} ${contact.number}"
                    })
                }
            }

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