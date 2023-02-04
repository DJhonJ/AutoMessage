package com.whatsmessage.ui.main

import android.content.Intent
import android.provider.Settings
import com.whatsmessage.R
import com.whatsmessage.domain.Message
import com.whatsmessage.ui.common.ModalDialog
import com.whatsmessage.ui.common.Util
import com.whatsmessage.usecases.GetMessages

class MainPresenter (private val getMessage: GetMessages) {
    suspend fun getListMessage(state: Int): List<Message> {
        return getMessage.getListMessage(state)
    }
}