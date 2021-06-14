package com.djhonj.automessage.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//podria llamarse ProgrammingReceiver
class AlertReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "INICIANDO PROCCESE DE ENVIO DE MENSAJE", Toast.LENGTH_SHORT).show()
    }
}