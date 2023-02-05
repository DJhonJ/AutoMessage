package com.whatsmessage

import android.app.Application
import androidx.room.Room
import com.whatsmessage.framework.database.AppDatabase

class App: Application()  {
    companion object {
        lateinit var appDb: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appDb = Room.databaseBuilder(this, AppDatabase::class.java, "database-app").build()
        initDependencyInjection()
    }
}