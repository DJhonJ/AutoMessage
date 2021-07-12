package com.djhonj.automessage.framework.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//podria llamarse ProgrammingReceiver
//inicia el proceso de envio
class AlertReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "INICIANDO PROCCESO DE ENVIO DE MENSAJE", Toast.LENGTH_SHORT).show()
    }
}