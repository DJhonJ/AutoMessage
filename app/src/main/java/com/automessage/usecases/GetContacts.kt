package com.automessage.usecases

import com.automessage.data.repository.ContactRepository
import com.automessage.domain.Contact

class GetContacts (private val repositoryContacts: ContactRepository) {
    suspend fun invoke(): List<Contact> {
        return repositoryContacts.getContacts()
    }
}