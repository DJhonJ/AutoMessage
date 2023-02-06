package com.whatsmessage.framework.database

import com.whatsmessage.domain.Message

interface ILocalMessage {
    suspend fun save(message: Message): Boolean
    suspend fun getListMessage(state: Int): List<Message>
    suspend fun update(message: Message): Boolean
}