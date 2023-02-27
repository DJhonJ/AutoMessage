package com.whatsmessage.framework.database

import android.util.Log
import com.whatsmessage.domain.Message
import com.whatsmessage.framework.datasource.ILocalMessage
import com.whatsmessage.framework.datasource.ISchedulerService
import com.whatsmessage.framework.toMessage
import com.whatsmessage.framework.toMessageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MessageDataSource(private val messageDao: IMessageDao?, private val schedulerService: ISchedulerService): ILocalMessage {
    override suspend fun save(message: Message): Boolean {
        try {
            if (schedulerService.schedule(message)) {
                messageDao?.insertMessage(message.toMessageEntity())
                return true
            }

            return false
        } catch (e: Exception) {
            Log.e("ERROR-SAVE", e.message.toString())
            return  false
        }
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