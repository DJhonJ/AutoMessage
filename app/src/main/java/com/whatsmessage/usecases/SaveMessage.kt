package com.whatsmessage.usecases

import com.whatsmessage.data.repository.MessageRepository
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message

class SaveMessage (private val messageRepository: MessageRepository) {
    suspend fun invoke(message: Message): Boolean = messageRepository.save(message)
}