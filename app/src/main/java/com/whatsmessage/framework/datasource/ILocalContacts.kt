package com.whatsmessage.framework.datasource

import com.whatsmessage.domain.Contact

interface ILocalContacts {
    suspend fun getContacts(): List<Contact>
}