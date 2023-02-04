package com.whatsmessage.ui.contact

import com.whatsmessage.domain.Contact
import com.whatsmessage.usecases.GetContacts
import kotlinx.coroutines.*

class ContactPresenter(private val getContactsUseCase: GetContacts) {
    suspend fun getContacts(): List<Contact> {
        var users: List<Contact>? = null

        users = getContactsUseCase.invoke()

        return users ?: listOf()
    }
}