package com.djhonj.automessage.usecases

import com.djhonj.automessage.data.repository.ContactRepository
import com.djhonj.automessage.domain.Contact

class GetContacts (private val repositoryContacts: ContactRepository) {
    suspend fun invoke(): List<Contact> {
        return repositoryContacts.getContacts()
    }
}