package com.automessage.ui.programming

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import com.automessage.R
import com.automessage.domain.Contact
import com.automessage.domain.DateTime
import com.automessage.domain.Message
import com.automessage.ui.common.*
import com.automessage.usecases.SaveMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.text.SimpleDateFormat

class ProgrammingPresenter(
    private val contextApp: Context,
    private val viewActivity: IViewActivity,
    private val programDispatch: SaveMessage) {

    @SuppressLint("SimpleDateFormat")
    suspend fun onSave(date: String, time: String, contact: Contact?, message: String) {
        if (date.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.date_send_empty))
            return
        }

        if (time.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.time_send_empty))
            return
        }

        if (contact == null) {
            viewActivity.showMessage(contextApp.getString(R.string.contact_send_empty))
            return
        }

        if (contact.name.isNullOrEmpty() || contact.number.isNullOrEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.contact_send_empty))
            return
        }

        if (message.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.message_send_empty))
            return
        }

        val dateTime = SimpleDateFormat("${Constants.DATE_FORMAT} ${Constants.TIME_FORMAT}").parse("$date $time")

        if (dateTime != null) {
            val messageId = generateMessageId()
            val messageDomain = Message(
                messageId,
                date,
                time,
                dateTime.time,
                contact.name,
                contact.number,
                message,
                0
            )
            val response: Boolean = programDispatch.invoke(messageDomain)

            withContext(Dispatchers.Main) {
                if (response) {
                    viewActivity.showMessage("SUCCESS") //envia al inicio
                    //view.initActivity() inicializa una actividad
                } else {
                    viewActivity.showMessage(contextApp.getString(R.string.message_validate_fields))
                }
            }
        } else {
            viewActivity.showMessage(contextApp.getString(R.string.message_validate_fields))
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

    fun onAssignContact(serializable: Serializable?): Contact? {
        if (serializable == null) {
            viewActivity.showMessage(contextApp.getString(R.string.again_select_contact))
            return null
        }

        viewActivity.showMessage("Contact selected")
        return serializable as Contact
    }

    private fun generateMessageId(): String {
        return ""
    }
}