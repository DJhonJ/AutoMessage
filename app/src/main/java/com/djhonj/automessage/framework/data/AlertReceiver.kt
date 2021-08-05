package com.djhonj.automessage.framework.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.djhonj.automessage.domain.InformationSend
import com.djhonj.automessage.framework.ui.common.Constants
import com.djhonj.automessage.framework.ui.main.MainActivity
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

//podria llamarse ProgrammingReceiver
//inicia el proceso de envio
class AlertReceiver : BroadcastReceiver() {
    //private val notificationApp: NotificationApp by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        //Toast.makeText(context, "INICIANDO PROCCESO DE ENVIO DE MENSAJE", Toast.LENGTH_SHORT).show()
        context?.let {
            val date = SimpleDateFormat("dd MMMM, yyyy HH:mm:ss").format(Date()).toString()

            context.let {
                it.stopService(Intent(it, MyService::class.java))
            }

            if (intent?.extras?.getSerializable("informationsend") == null) {
                NotificationApp(it).send("proceso anulado", "tuvimos problemas con el envio... $date")
                return
            }

            val intentWhatsapp = intent?.let {
                val infoSend: InformationSend? = it.extras?.getSerializable("informationsend") as InformationSend?

                Intent().apply {
                    action = Intent.ACTION_VIEW
                    `package` = "com.whatsapp"
                    data = Uri.parse("whatsapp://send?phone=${infoSend!!.numberSelected}&text=${infoSend!!.message}")
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

            context.startActivity(intentWhatsapp)

            NotificationApp(it).send("Iniciando proceso", "procesando... $date")
        }
    }
}