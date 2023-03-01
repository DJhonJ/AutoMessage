package com.whatsmessage.ui.programming

import android.content.Context
import android.content.Intent
import com.whatsmessage.R
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message
import com.whatsmessage.ui.common.*
import com.whatsmessage.ui.main.MainActivity
import com.whatsmessage.usecases.SaveMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.Serializable
import java.util.*

class ProgrammingPresenter(
    private val contextApp: Context,
    private val viewActivity: IActivityView,
    private val saveMessageUseCase: SaveMessage) {

    private var contacts: MutableList<Contact> = mutableListOf()

    suspend fun save(date: String, dateShowUser: String, time: String, messageText: String) {
        if (date.trim().isEmpty()) {
            viewActivity.onShowMessage(contextApp.getString(R.string.date_send_empty))
            return
        }

        if (time.trim().isEmpty()) {
            viewActivity.onShowMessage(contextApp.getString(R.string.time_send_empty))
            return
        }

        if (contacts.size == 0) {
            viewActivity.onShowMessage(contextApp.getString(R.string.contact_send_empty))
            return
        }

        if (messageText.trim().isEmpty()) {
            viewActivity.onShowMessage(contextApp.getString(R.string.message_send_empty))
            return
        }

        val current: String = Util.getCurrentDate(Constants.DATE_TIME_FORMAT)

        if (current.isEmpty()) {
            viewActivity.onShowMessage(contextApp.getString(R.string.message_validate_fields))
            return
        }

        val phone = ""
        val message = Message(0, phone, date, dateShowUser, time, current, messageText, contacts, 0)
        val response = GlobalScope.async(Dispatchers.IO) { saveMessageUseCase.invoke(message) }

        if (response.await()) {
            viewActivity.onShowMessage(contextApp.getString(R.string.message_success_programmacion))

            viewActivity.onStartActivity(Intent(contextApp, MainActivity::class.java).apply {
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        } else {
            viewActivity.onShowMessage(contextApp.getString(R.string.message_validate_fields))
        }
    }

    fun addContact(serializable: Serializable?): Contact? {
        val contact: Contact? = validateSerializable(serializable)

        if (contact == null) {
            viewActivity.onShowMessage(contextApp.getString(R.string.again_select_contact))
            return null
        }

        if (existsContact(contact.phone)) {
            viewActivity.onShowMessage(contextApp.getString(R.string.exists_select_contact))
            return null
        }

        contacts.add(contact)

        return contact
    }

    fun removeContact(number: String){
        contacts.filter { it.phone == number }.forEach { contacts.remove(it) }
    }

    private fun existsContact(number: String) : Boolean {
        //val predicate = Predicate { _number : String -> _number == number }
        //contacts.any { predicate.test(it.number) }
        return contacts.any { it.phone == number }
    }

    private fun validateSerializable(serializable: Serializable?): Contact? {
        if (serializable == null) {
            return null
        }

        return serializable as Contact
    }
}