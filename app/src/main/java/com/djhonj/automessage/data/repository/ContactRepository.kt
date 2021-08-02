package com.djhonj.automessage.data.repository

import com.djhonj.automessage.data.datasource.ILocalContacts

class ContactRepository(private val localContact: ILocalContacts) {
    suspend fun getContacts() = localContact.getContacts()
}