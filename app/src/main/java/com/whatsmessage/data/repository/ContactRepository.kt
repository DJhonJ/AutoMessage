package com.whatsmessage.data.repository

import com.whatsmessage.data.datasource.ILocalContacts

class ContactRepository(private val localContact: ILocalContacts) {
    suspend fun getContacts() = localContact.getContacts()
}