package com.djhonj.automessage.framework.ui.contact

import com.djhonj.automessage.domain.Contact
import com.djhonj.automessage.usecases.GetContacts
import kotlinx.coroutines.*

class ContactPresenter(private val getContactsUseCase: GetContacts) {
    fun getContacts(): List<Contact> {
        var users: List<Contact>? = null

        runBlocking {
            users = getContactsUseCase.invoke()
        }

        return users ?: listOf()
    }
}