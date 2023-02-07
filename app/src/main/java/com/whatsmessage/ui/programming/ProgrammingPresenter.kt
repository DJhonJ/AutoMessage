package com.whatsmessage.ui.programming

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.whatsmessage.R
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message
import com.whatsmessage.ui.common.*
import com.whatsmessage.ui.main.MainActivity
import com.whatsmessage.usecases.SaveMessage
import java.io.Serializable
import java.text.SimpleDateFormat
import kotlin.random.Random

class ProgrammingPresenter(
    private val contextApp: Context,
    private val viewActivity: IViewActivity,
    private val programDispatch: SaveMessage) {

    private var contacts: MutableList<Contact> = mutableListOf()

    @SuppressLint("SimpleDateFormat")
    suspend fun save(date: String, time: String, message: String) {
        if (date.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.date_send_empty))
            return
        }

        if (time.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.time_send_empty))
            return
        }

        if (contacts.size <= 0) {
            viewActivity.showMessage(contextApp.getString(R.string.contact_send_empty))
            return
        }

        if (message.trim().isEmpty()) {
            viewActivity.showMessage(contextApp.getString(R.string.message_send_empty))
            return
        }

        val dateTime = SimpleDateFormat("${Constants.DATE_FORMAT} ${Constants.TIME_FORMAT}").parse("$date $time")

        if (dateTime == null) {
            viewActivity.showMessage(contextApp.getString(R.string.message_validate_fields))
            return
        }

        val messageId = generateMessageId()
        val messageDomain = Message(messageId, date, time, dateTime.time, contacts, message, 0)
        val response: Boolean = programDispatch.invoke(messageDomain)

        if (response) {
            viewActivity.showMessage(contextApp.getString(R.string.message_success_programmacion)) //envia al inicio
            viewActivity.onStartActivity(Intent(contextApp, MainActivity::class.java).apply {
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        } else {
            viewActivity.showMessage(contextApp.getString(R.string.message_validate_fields))
        }
    }

    fun addContact(serializable: Serializable?): Contact? {
        val contact: Contact? = validateSerializable(serializable)

        if (contact == null) {
            viewActivity.showMessage(contextApp.getString(R.string.again_select_contact))
            return null
        }

        if (existsContact(contact.number)) {
            viewActivity.showMessage(contextApp.getString(R.string.exists_select_contact))
            return null
        }

        contacts.add(contact)

        return contact
    }

    fun removeContact(number: String){
        contacts.filter { it.number == number.toString() }.forEach { contacts.remove(it) }
    }

    private fun existsContact(number: String) : Boolean {
        //val predicate = Predicate { _number : String -> _number == number }
        //contacts.any { predicate.test(it.number) }
        return contacts.any { it.number == number }
    }

    private fun validateSerializable(serializable: Serializable?): Contact? {
        if (serializable == null) {
            return null
        }

        return serializable as Contact
    }

    private fun generateMessageId(): Int {
        return Random.nextInt(0, 9999) + Random.nextInt(10000, 20000)
    }
}