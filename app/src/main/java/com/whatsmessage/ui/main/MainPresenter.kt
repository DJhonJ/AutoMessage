package com.whatsmessage.ui.main

import com.whatsmessage.domain.Message
import com.whatsmessage.usecases.GetMessages

class MainPresenter (private val getMessage: GetMessages) {
    suspend fun getListMessage(state: Int): List<Message> {
        return getMessage.getListMessage(state)
    }
}