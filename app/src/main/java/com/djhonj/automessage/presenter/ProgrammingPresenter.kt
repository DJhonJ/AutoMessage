package com.djhonj.automessage.presenter

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.djhonj.automessage.interactor.ProgrammingInteractor
import com.djhonj.automessage.interfaces.IProgrammingInteractor
import com.djhonj.automessage.interfaces.IProgrammingPresenter
import com.djhonj.automessage.interfaces.IShowMessage
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ProgrammingPresenter(private val view: IShowMessage): IProgrammingPresenter {
    private val interactorProgrammin: IProgrammingInteractor = ProgrammingInteractor(this)

    //recibo datos de fecha y hora
    @RequiresApi(Build.VERSION_CODES.O)
    override fun save(dateTime: String) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, dateTime.split(':')[0].toInt())
            set(Calendar.MINUTE, dateTime.split(':')[1].toInt())
            set(Calendar.YEAR, LocalDate.now().year)
            //set(Calendar.MONTH, LocalDate.now().monthValue)
            set(Calendar.DAY_OF_MONTH, LocalDate.now().dayOfMonth)
        }

        interactorProgrammin.save(view as Activity,  calendar.timeInMillis)
    }

    override fun show(message: String) {
        view.show(message)
    }
}