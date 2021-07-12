package com.djhonj.automessage.framework

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }
}