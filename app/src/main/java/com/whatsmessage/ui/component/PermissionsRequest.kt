package com.whatsmessage.ui.component

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.whatsmessage.R
import com.whatsmessage.ui.common.Constants
import com.whatsmessage.ui.common.IActivityView

class PermissionsRequest (private val activity: IActivityView) {
    fun checkPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(activity.getInstance(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Shows dialogs from request permissions
     */
    fun checkPermissionsShowDialog(): Boolean {
        val check = checkPermissions()

        if (!check) {
            requestPermissions()
        }

        return check
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            Constants.CODE_CONTACT_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //action permision
                }
            }
        }
    }

    private fun requestPermissions() {
        val instance = activity.getInstance()

        if (ActivityCompat.shouldShowRequestPermissionRationale(instance, Manifest.permission.READ_CONTACTS)) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", instance.packageName, null)).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                Intent.FLAG_ACTIVITY_NEW_TASK
                Intent.FLAG_ACTIVITY_NO_HISTORY
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            }

            val dialog = ModalDialog(instance,
                message = instance.getString(R.string.text_contacts_permissions),
                confirmText = instance.getString(R.string.text_settings),
                cancelText = instance.getString(R.string.text_cancel),
                listenerConfirm =  { _: DialogInterface, _: Int -> activity.onStartActivity(intent) }
            )

            activity.onShowModalDialog(dialog, "")
        } else {
            ActivityCompat.requestPermissions(instance, arrayOf(Manifest.permission.READ_CONTACTS),
                Constants.CODE_CONTACT_PERMISSION
            )
        }
    }
}