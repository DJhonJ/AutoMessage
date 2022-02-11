package com.automessage.ui.common

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.automessage.ui.common.Util.Companion.showAllowingStateLoss
import java.lang.Exception
import java.lang.IllegalStateException

class ModalDialog(
    private val context: Context,
    private val title: String = "",
    private val message: String = "",
    private val confirmText: String = "ok",
    private val cancelText: String? = null,
    private val listenerConfirm: (dialog: DialogInterface, id: Int) -> Unit = { _, _ -> },
    private val listenerCancel: (dialog: DialogInterface, id: Int) -> Unit = { _, _ -> }
) {
    private val builder = AlertDialog.Builder(context)

    fun show() {
        create()
        builder.show()
    }

    private fun create() {
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(confirmText, listenerConfirm)

        if (cancelText != null) {
            builder.setNegativeButton(cancelText, listenerCancel)
        }

        builder.create()
    }
}