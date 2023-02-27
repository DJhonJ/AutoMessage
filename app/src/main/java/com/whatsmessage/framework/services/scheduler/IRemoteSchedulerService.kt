package com.whatsmessage.framework.services.scheduler

import com.whatsmessage.domain.MessageEncrypted
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IRemoteSchedulerService {
    @POST("save-message")
    suspend fun schedule(@Body message: MessageEncrypted): Response<Void>
}