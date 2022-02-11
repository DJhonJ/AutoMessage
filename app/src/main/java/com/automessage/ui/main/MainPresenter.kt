package com.automessage.ui.main

import android.content.Intent
import android.provider.Settings
import com.automessage.R
import com.automessage.ui.common.ModalDialog
import com.automessage.ui.common.Util

class MainPresenter (private val mainActivity: MainActivity) {
    fun onVerifyAccessibility() {
        if (!Util.isAccessibilityEnable(mainActivity.applicationContext)) {
//            val modal = ModalDialog(
//                null,
//                title = mainActivity.getString(R.string.app_name),
//                message = mainActivity.getString(R.string.accessibility_enable_message),
//                confirmText = mainActivity.getString(R.string.accessibility_enable),
//                listenerConfirm = { _,_ ->
//                    mainActivity.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
//                }
//            )
//
//            mainActivity.onShowModalDialog(modal)
        }
    }
}