package com.djhonj.automessage.view.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

//listener se ejecuta desde el main
class DateDialogFragment(val listener: (date: String) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    //se llama cuando el usuario seleccione una fecha
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        listener(DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.time))

        //listener(dayOfMonth, month, year)
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
            calendar.add(Calendar.MONTH, +2)
            datePicker.maxDate = calendar.timeInMillis
        }

        return picker
    }
}