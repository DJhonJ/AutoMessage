package com.automessage.framework

import android.app.Application
import com.automessage.framework.data.NotificationApp
import org.koin.android.ext.android.inject

class App: Application()  {
    private val notificationApp: NotificationApp by inject()

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()

        notificationApp.createChannel()
    }
}