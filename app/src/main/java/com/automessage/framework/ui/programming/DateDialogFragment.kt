package com.automessage.framework.ui.programming

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.automessage.framework.ui.common.Constants
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//listener se ejecuta desde el main
class DateDialogFragment(val listener: (date: String) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    //se llama cuando el usuario seleccione una fecha
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        //listener(DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.time))
        listener(SimpleDateFormat(Constants.DATE_FORMAT).format(calendar.time))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        //obtenemos la fecha actual
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        //pasamos this, porque heredamos de ondatesetlistener
        val picker = DatePickerDialog(activity as Context, this, year, month, day).apply {
            datePicker.minDate = calendar.timeInMillis
            calendar.add(Calendar.MONTH, 1)
            datePicker.maxDate = calendar.timeInMillis
        }

        return picker
    }
}