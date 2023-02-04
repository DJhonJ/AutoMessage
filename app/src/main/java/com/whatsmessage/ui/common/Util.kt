package com.whatsmessage.ui.common

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.whatsmessage.framework.ServiceAccessibility
import org.koin.ext.getFullName

class Util {
    companion object {
        fun isAccessibilityEnable(context: Context): Boolean {
            val accessibilityEnabled: String? = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ACCESSIBILITY_ENABLED
            )

            if (!accessibilityEnabled.isNullOrEmpty() && accessibilityEnabled == "1") {
                val enabledServicesSetting: String? = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
                )

                if (enabledServicesSetting != null && enabledServicesSetting.isNotEmpty()) {
                    return enabledServicesSetting.contains(ServiceAccessibility::class.getFullName())
                }
            }

            return false
        }
    }
}