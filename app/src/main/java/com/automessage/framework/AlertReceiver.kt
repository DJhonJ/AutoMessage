package com.automessage.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.automessage.domain.Information
import java.text.SimpleDateFormat
import java.util.*

//podria llamarse ProgrammingReceiver
//inicia el proceso de envio
class AlertReceiver : BroadcastReceiver() {
    //private val notificationApp: NotificationApp by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val date = SimpleDateFormat("dd MMMM, yyyy HH:mm:ss").format(Date()).toString()

            context.let {
                it.stopService(Intent(it, MyService::class.java))
            }

            if (intent?.extras?.getSerializable("informationsend") == null) {
                NotificationApp(it).send("proceso anulado", "tuvimos problemas con el envio... $date")
                return
            }

            val whatsappIntent = intent?.let {
                val information: Information? = it.extras?.getSerializable("informationsend") as Information?

                Intent().apply {
                    action = Intent.ACTION_VIEW
                    `package` = "com.whatsapp"
                    data = Uri.parse("whatsapp://send?phone=${information!!.phone}&text=${information!!.message}")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
            }

//            val intentWhatsapp = Intent().apply {
//                action = Intent.ACTION_SEND
//                `package` = "com.whatsapp"
//                putExtra(Intent.EXTRA_TEXT, "escribiendo mensaje")
//                type = "text/plain"
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            }

            context.startActivity(whatsappIntent)

            NotificationApp(it).send("Iniciando proceso", "procesando... $date")
        }
    }
}