package com.whatsmessage.framework.providers

import com.whatsmessage.domain.Contact

interface ILocalContacts {
    suspend fun getContacts(): List<Contact>
}