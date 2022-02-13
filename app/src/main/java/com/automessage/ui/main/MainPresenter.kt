package com.automessage.ui.main

import android.content.Intent
import android.provider.Settings
import com.automessage.R
import com.automessage.domain.Message
import com.automessage.ui.common.ModalDialog
import com.automessage.ui.common.Util
import com.automessage.usecases.GetMessages

class MainPresenter (private val getMessage: GetMessages) {
    suspend fun getListMessage(state: Int): List<Message> {
        return getMessage.getListMessage(state)
    }
}