package com.automessage.usecases

import com.automessage.data.repository.MessageRepository
import com.automessage.domain.Message

class SaveMessage (private val messageRepository: MessageRepository) {
    suspend fun invoke(message: Message): Boolean {
        if (message.phones.isNullOrEmpty() || message.message.isEmpty() || message.dateTimeMillisecond < 0) {
            return false
        }

        if (message.phones!!.contains("+")) {
            message.apply {
                phones = phones!!.replace("+", "")
            }
        }

        return messageRepository.save(message)
        //return true
    }
}