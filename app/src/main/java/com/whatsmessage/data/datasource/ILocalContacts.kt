package com.whatsmessage.data.datasource

import com.whatsmessage.domain.Contact

interface ILocalContacts {
    suspend fun getContacts(): List<Contact>
}