package com.automessage.ui.programming

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import com.automessage.R
import com.automessage.domain.DateTime
import com.automessage.ui.common.*
import com.automessage.usecases.SaveMessage
import java.text.SimpleDateFormat

class ProgrammingPresenter(
    private val contextApp: Context,
    private val viewActivity: IViewActivity,
    private val programDispatch: SaveMessage) {

    fun onSave(date: String, time: String, phone: String?, message: String) {
        if (date.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.date_send_empty))
            return
        }

        if (time.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.time_send_empty))
            return
        }

        if (phone.isNullOrEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.phone_send_empty))
            return
        }

        if (message.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.message_send_empty))
            return
        }

        val dateTime =
            SimpleDateFormat("${Constants.DATE_FORMAT} ${Constants.TIME_FORMAT}").parse("$date $time")
        val response: Boolean = programDispatch.invoke(
            dateTime.time,
            DateTime(date, time),
            phone, message
        )

        if (response) {
            viewActivity.showMessage("SUCCESS") //envia al inicio
            //view.initActivity() inicializa una actividad
        } else {
            viewActivity.showMessage("Diligenciar los campos de manera correcta.")
        }
    }

    fun onVerifyAccessibility(context: Context): Boolean {
        if (!Util.isAccessibilityEnable(context)) {
            val listener = { _: DialogInterface, _: Int ->
                context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }

            val modal = ModalDialog (
                context,
                context.getString(R.string.information),
                context.getString(R.string.accessibility_enable_message),
                context.getString(R.string.accessibility_enable),
                listenerConfirm = listener)

            viewActivity.onShowModalDialog(modal, "accessibility_enable")
            return false
        }

        return true
    }
}