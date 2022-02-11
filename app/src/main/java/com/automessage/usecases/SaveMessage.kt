package com.automessage.usecases

import com.automessage.domain.DateTime

class SaveMessage {
    fun invoke(dateTimeMillisecond: Long, dateTimeInfo: DateTime, phone: String?, message: String): Boolean {
        if (phone.isNullOrEmpty() || message.isEmpty() || dateTimeMillisecond < 0) {
            return false
        }

        //hacer llamado al respository para entrar en layer framewoek y hacer envio
        return true
    }
}