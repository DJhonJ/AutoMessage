package com.whatsmessage.framework.services.scheduler

import retrofit2.http.Body
import retrofit2.http.POST

interface IRemoteSchedulerService {
    @POST("api/v1/scheduler")
    suspend fun schedule(@Body body: String): Map<String, String>
}