package com.djhonj.automessage.framework.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
        context.let {
            val date = SimpleDateFormat("dd MMMM, yyyy HH:mm:ss").format(Date()).toString()

            context.let {
                it!!.stopService(Intent(it, MyService::class.java))
            }

            NotificationApp(it!!).send("Iniciando proceso", "procesando... $date")
        }
    }
}