package com.whatsmessage.usecases

import com.whatsmessage.data.repository.MessageRepository
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message

class SaveMessage (private val messageRepository: MessageRepository) {
    suspend fun invoke(message: Message): Boolean {
        if (message.contacts.isNullOrEmpty() || message.message.isEmpty() || message.dateTimeMillisecond < 0) {
            return false
        }

        message.apply {
            contacts = message.contacts.map { c -> Contact(c.name, c.number.replace("+", "")) }
        }

        return messageRepository.save(message)
        //return true
    }
}