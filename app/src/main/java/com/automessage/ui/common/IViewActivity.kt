package com.automessage.ui.common


interface IViewActivity {
    fun showMessage(message: String)
    fun onShowModalDialog(modalDialogFragment: ModalDialog, tag: String)
}