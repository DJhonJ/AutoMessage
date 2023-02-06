package com.whatsmessage.data.repository

import com.whatsmessage.framework.providers.ILocalContacts

class ContactRepository(private val localContact: ILocalContacts) {
    suspend fun getContacts() = localContact.getContacts()
}