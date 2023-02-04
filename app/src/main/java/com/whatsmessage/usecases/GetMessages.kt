package com.whatsmessage.usecases

import com.whatsmessage.data.repository.MessageRepository
import com.whatsmessage.domain.Message

class GetMessages(private val repository: MessageRepository) {
    suspend fun getListMessage(state: Int): List<Message> = repository.getListMessage(state)
}