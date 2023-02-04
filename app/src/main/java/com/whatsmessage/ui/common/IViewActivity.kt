package com.whatsmessage.ui.common

import android.content.Intent


interface IViewActivity {
    fun showMessage(message: String)
    fun onShowModalDialog(modalDialogFragment: ModalDialog, tag: String)
    fun onStartActivity(intent: Intent)
}