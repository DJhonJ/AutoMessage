package com.automessage.usecases

import com.automessage.data.repository.MessageRepository
import com.automessage.domain.Contact
import com.automessage.domain.Message

class SaveMessage (private val messageRepository: MessageRepository) {
    suspend fun invoke(message: Message): Boolean {
        if ((message.contacts.size < 0 && message.contacts.isNullOrEmpty()) || message.message.isEmpty() || message.dateTimeMillisecond < 0) {
            return false
        }

        message.apply {
            contacts = message.contacts.map { c -> Contact(c.name, c.number.replace("+", "")) }
        }

        return messageRepository.save(message)
        //return true
    }
}