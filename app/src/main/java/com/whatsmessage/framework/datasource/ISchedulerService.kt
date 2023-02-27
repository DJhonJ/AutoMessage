package com.whatsmessage.framework.datasource

import com.whatsmessage.domain.Message

interface ISchedulerService {
    suspend fun schedule(message: Message) : Boolean
}