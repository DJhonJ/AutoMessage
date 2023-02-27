package com.whatsmessage.ui.common

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Util {
    companion object {
        fun getCurrentDate(pattern: String) : String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            LocalDateTime.now().format(formatter)
        } else {
            SimpleDateFormat(pattern, Locale("es")).format(Date())
        }
    }
}