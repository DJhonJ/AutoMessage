package com.whatsmessage.ui.common

import android.app.Activity
import android.content.Intent
import com.whatsmessage.ui.component.ModalDialog

interface IActivityView {
    fun getInstance(): Activity
    fun onStartActivity(intent: Intent)
    fun onShowMessage(message: String)
    fun onShowModalDialog(modalDialog: ModalDialog, tag: String)
}