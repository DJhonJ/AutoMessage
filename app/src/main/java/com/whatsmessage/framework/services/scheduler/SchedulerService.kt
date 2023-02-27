package com.whatsmessage.framework.services.scheduler

import android.util.Log
import com.google.gson.Gson
import com.whatsmessage.domain.Message
import com.whatsmessage.domain.MessageEncrypted
import com.whatsmessage.framework.common.Encryption
import com.whatsmessage.framework.datasource.ISchedulerService
import com.whatsmessage.framework.services.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.servicestack.client.MimeTypes.Json
import java.io.Serializable

class SchedulerService: ISchedulerService {
    private val serviceBuilder by lazy { ServiceBuilder.build(IRemoteSchedulerService::class.java) }

    override suspend fun schedule(message: Message): Boolean {
        val messageJson: String = Gson().toJson(message)
        val encrypted: String = Encryption.encrypt(messageJson)
        val messageSend = MessageEncrypted(encrypted)

        val s = serviceBuilder.schedule(messageSend).code() == 200

        Log.i("MESSAGE", s.toString())

        return s
    }
}