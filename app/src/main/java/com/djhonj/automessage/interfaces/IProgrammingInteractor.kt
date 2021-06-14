package com.djhonj.automessage.interfaces

import android.app.Activity

interface IProgrammingInteractor: IShowMessage {
    fun save(activity: Activity, dateTimeMilliSeconds: Long)
}