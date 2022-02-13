package com.automessage

import android.app.Application
import androidx.room.Room
import com.automessage.framework.database.AppDatabase
import com.automessage.framework.initDependencyInjection

class App: Application()  {
    companion object {
        lateinit var appDb: AppDatabase
    }

    //private val notificationApp: NotificationApp by inject()

    override fun onCreate() {
        super.onCreate()
        appDb = Room.databaseBuilder(this, AppDatabase::class.java, "database-app").build()
        initDependencyInjection()
        //notificationApp.createChannel()
    }
}