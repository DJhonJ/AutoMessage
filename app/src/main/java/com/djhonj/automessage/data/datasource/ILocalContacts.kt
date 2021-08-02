package com.djhonj.automessage.data.datasource

import com.djhonj.automessage.domain.Contact

interface ILocalContacts {
    suspend fun getContacts(): List<Contact>
}