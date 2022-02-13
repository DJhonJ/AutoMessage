package com.automessage.usecases

import com.automessage.data.repository.MessageRepository
import com.automessage.domain.Message

class GetMessages(private val repository: MessageRepository) {
    suspend fun getListMessage(state: Int): List<Message> = repository.getListMessage(state)
}