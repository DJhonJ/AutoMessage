package com.whatsmessage.ui.programming

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.whatsmessage.ui.common.Constants
import java.text.SimpleDateFormat
import java.util.*

class TimeDialogFragment(val listener: (String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        listener(SimpleDateFormat(Constants.TIME_FORMAT).format(calendar.time))
        //listener(DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity as Context, this, hour, minute, true)
    }
}