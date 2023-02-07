package com.whatsmessage.framework.services.scheduler

import com.whatsmessage.framework.datasource.ISchedulerService
import com.whatsmessage.framework.services.ServiceBuilder

class SchedulerService: ISchedulerService {
    private val serviceBuilder by lazy { ServiceBuilder.build(IRemoteSchedulerService::class.java) }

    override suspend fun schedule() {
        val response: Map<String, String> = serviceBuilder.schedule("")
    }
}