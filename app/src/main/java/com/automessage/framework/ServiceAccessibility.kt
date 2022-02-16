package com.automessage.framework

import android.accessibilityservice.AccessibilityGestureEvent
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.automessage.R

class ServiceAccessibility: AccessibilityService() {
    lateinit var constraintLayout: FrameLayout

    //interumpe los comentarios que podamos hacer en la aplicacion
    override fun onInterrupt() {
        message("interrupiendo los cometarios hechos en la app")
    }

    //se llama cuando el usuario hace algun evento (como clickar) sobre el dispositivo
    //en ese momento podemos hacer comentarios
    //@RequiresApi(Build.VERSION_CODES.P)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        when(event?.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                message("clicked")
            }

            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                message("focus")
                //performClick()
                //val activity: ActivityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            }
        }

//        val source: AccessibilityNodeInfo = event?.source ?: return
//
//        if (source != null) {
//            if (source.childCount > 0) {
//                //message(source.getChild(0).text.toString())
//
//                message(source.text.toString())
//                message(source.tooltipText.toString())
//                message(source.getChild(0).text.toString())
//
//            }
//        }

        //performGlobalAction(AccessibilityNodeInfo.ACTION_CLICK)

        //message(event?.contentDescription.toString())
    }

    //El sistema llama a este método cuando se conecta correctamente a tu servicio de accesibilidad\
    //Usa este método para completar cualquier paso de configuración único para tu servicio
    override fun onServiceConnected() {
        //super.onServiceConnected()
        message(applicationContext.getString(R.string.accessibility_enabled_state))
        //message("serviceConnected con this")

        var info = AccessibilityServiceInfo()
        info.apply {
            //notificationTimeout = 100
            //canRetrieveWindowContent = true
        }

        //this.serviceInfo

        /*constraintLayout = FrameLayout(this)

        val inflater: LayoutInflater = LayoutInflater.from(this)
        inflater.inflate(R.layout.activity_main, constraintLayout);*/
    }

    //Se llama a este método cuando el sistema está a punto de cerrar el servicio de accesibilidad
    override fun onUnbind(intent: Intent?): Boolean {
        message(applicationContext.getString(R.string.accessibility_disabled_state))
        //return super.onUnbind(intent)
        return false
    }

    private fun performClick() {
        //informacion de la vista (activity, pantalla) con el focus actual
        val nodeInfo: AccessibilityNodeInfo = this.rootInActiveWindow
        val targetNode: AccessibilityNodeInfo? = findNodeInfoById(nodeInfo, "com.whatsapp:id/send")

        targetNode?.also {
            if (it.isClickable && it.isVisibleToUser) {
                targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
        }
    }

    private fun findNodeInfoById(nodeInfo: AccessibilityNodeInfo, resId: String): AccessibilityNodeInfo? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val list: List<AccessibilityNodeInfo> = nodeInfo.findAccessibilityNodeInfosByViewId(resId)
            //nodeInfo.in
            if(list != null && list.isNotEmpty()) {
                return list?.get(0)
            }
        }

        return null
    }

    private fun message(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGesture(gestureEvent: AccessibilityGestureEvent): Boolean {
        return super.onGesture(gestureEvent)
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

        message("configurationHome")
    }
}