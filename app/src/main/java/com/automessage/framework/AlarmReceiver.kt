package com.automessage.framework

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.PowerManager
import android.os.Vibrator
import android.widget.Toast
import com.automessage.domain.Message
import com.automessage.ui.programming.WakeUpActivity
import java.util.*


//podria llamarse ProgrammingReceiver
//inicia el proceso de envio
class AlarmReceiver : BroadcastReceiver() {
    //private val notificationApp: NotificationApp by inject()

    //@SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { ctx ->
            //Toast.makeText(ctx, "ALARM", Toast.LENGTH_SHORT).show()

            //val vibrator = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            //vibrator.vibrate(3000)

            ctx.startActivity(Intent(ctx, WakeUpActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("messageSend", intent?.extras?.getSerializable("messageSend"))
            })

//            val powerManager = ctx.getSystemService(Context.POWER_SERVICE) as PowerManager
//            val powerWakeLock = powerManager.newWakeLock(
//                PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE,
//                "test"
//            )
//
//            powerWakeLock.acquire()
//            powerWakeLock.release()
//
//            //val date = SimpleDateFormat("dd MMMM, yyyy HH:mm:ss").format(Date()).toString()
//            // it.stopService(Intent(it, MyService::class.java))
//            //}
//
//            val message = intent?.extras?.getSerializable("messageSend") as Message?
//
//            message?.let { msg ->
//                val _intent = Intent().apply {
//                    action = Intent.ACTION_VIEW
//                    `package` = "com.whatsapp"
//                    data =
//                        Uri.parse("whatsapp://send?phone=${msg.phone}&text=${msg.message}")
//                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                }
//
//                ctx.startActivity(_intent)
//            }

            //NotificationApp(it).send("Iniciando proceso", "procesando... $date")
            //powerWakeLock.release()
        }
    }
}