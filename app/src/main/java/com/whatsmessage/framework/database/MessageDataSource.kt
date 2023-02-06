package com.whatsmessage.framework.database

import android.util.Log
import com.whatsmessage.domain.Message
import com.whatsmessage.framework.toMessage
import com.whatsmessage.framework.toMessageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MessageDataSource(private val messageDao: IMessageDao?): ILocalMessage {
    override suspend fun save(message: Message): Boolean {
        try {
            //i call service scheduler
            withContext(Dispatchers.IO) {
                messageDao?.insertMessage(message.toMessageEntity())
            }
        } catch (e: Exception) {
            Log.e("error-save", e.message.toString())
            return  false
        }

        return true
    }

    override suspend fun getListMessage(state: Int): List<Message> {
        val messages: List<Message>? = withContext(Dispatchers.IO) {
            messageDao?.getListMessage(state)?.map { it.toMessage() }
        }

        return messages ?: arrayListOf()
    }

    override suspend fun update(message: Message): Boolean {
        TODO("Not yet implemented")
    }
}