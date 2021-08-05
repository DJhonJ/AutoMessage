package com.automessage.data.repository

import com.automessage.data.datasource.ILocalContacts

class ContactRepository(private val localContact: ILocalContacts) {
    suspend fun getContacts() = localContact.getContacts()
}