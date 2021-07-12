package com.djhonj.automessage.framework.data

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityEvent
import android.widget.FrameLayout
import android.widget.Toast
import com.djhonj.automessage.R

class MyAccessibilityService: AccessibilityService() {
    lateinit var constraintLayout: FrameLayout

    //interumpe los comentarios que podamos hacer en la aplicacion
    override fun onInterrupt() {
        //
    }

    //se llama cuando el usuario hace algun evento (como clickar) sobre el dispositivo
    //en ese momento podemos hacer comentarios
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        /*Toast.makeText(applicationContext, "Mensaje gg", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Mensaje 2 gg ", Toast.LENGTH_SHORT).show()*/

        when(event?.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, "focused", Toast.LENGTH_SHORT).show()
        }
    }

    //El sistema llama a este método cuando se conecta correctamente a tu servicio de accesibilidad\
    //Usa este método para completar cualquier paso de configuración único para tu servicio
    override fun onServiceConnected() {
        //super.onServiceConnected()
        Toast.makeText(applicationContext, "serviceConnected", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "serviceConnected this", Toast.LENGTH_LONG).show()

        constraintLayout = FrameLayout(this)

        val inflater: LayoutInflater = LayoutInflater.from(this)
        inflater.inflate(R.layout.activity_main, constraintLayout);
    }

    //Se llama a este método cuando el sistema está a punto de cerrar el servicio de accesibilidad
    override fun onUnbind(intent: Intent?): Boolean {
        //return super.onUnbind(intent)
        return false
    }

    private fun configurateHomeButton() {
        //if (constraintLayout == null) {
           // Log.i("configurateHomeButton", "constraintLayout vacio")
        //}

        //val button: Button = constraintLayout.findViewById(R.id.buttonHome)

//        if (button == null) {
//            Log.i("configurateHomeButton", "button vacio")
//        }
//
//        button.let {
//            it.setOnClickListener {
//                performGlobalAction(GLOBAL_ACTION_HOME)
//
//                Toast.makeText(applicationContext, "click button", Toast.LENGTH_LONG).show()
//            }
//        }

        Toast.makeText(applicationContext, "configurationHome", Toast.LENGTH_LONG).show()
    }
}