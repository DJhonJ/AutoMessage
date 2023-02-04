package com.whatsmessage.usecases

import com.whatsmessage.data.repository.ContactRepository
import com.whatsmessage.domain.Contact

class GetContacts (private val repositoryContacts: ContactRepository) {
    suspend fun invoke(): List<Contact> {
        return repositoryContacts.getContacts()
    }
}