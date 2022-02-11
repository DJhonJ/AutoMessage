package com.automessage.ui.contact

import com.automessage.domain.Contact
import com.automessage.usecases.GetContacts
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