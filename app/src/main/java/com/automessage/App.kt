package com.automessage

import android.app.Application
import com.automessage.framework.NotificationApp
import com.automessage.framework.initDependencyInjection
import org.koin.android.ext.android.inject

class App: Application()  {
    private val notificationApp: NotificationApp by inject()

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()

        notificationApp.createChannel()
    }
}