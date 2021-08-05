package com.automessage.data.datasource

import com.automessage.domain.Contact

interface ILocalContacts {
    suspend fun getContacts(): List<Contact>
}