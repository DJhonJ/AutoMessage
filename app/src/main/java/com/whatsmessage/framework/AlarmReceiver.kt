package com.whatsmessage.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.whatsmessage.domain.Message

//podria llamarse ProgrammingReceiver
//inicia el proceso de envio
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { ctx ->
            val message = intent?.extras?.getSerializable("messageSend") as Message?

            message?.let {
                Toast.makeText(ctx, "ALARM ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}