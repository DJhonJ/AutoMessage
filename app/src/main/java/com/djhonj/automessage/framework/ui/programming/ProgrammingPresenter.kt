package com.djhonj.automessage.framework.ui.programming

import android.os.Build
import androidx.annotation.RequiresApi
import com.djhonj.automessage.framework.ui.common.Constants
import com.djhonj.automessage.framework.ui.common.IShowMessage
import com.djhonj.automessage.usecases.ProgramarEnvio
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class ProgrammingPresenter(
    private val view: IProgrammingView,
    private val programarEnvio: ProgramarEnvio
    ) {
    //private val interactorProgrammin: IProgrammingInteractor = ProgrammingInteractor(this)
    //private val programarEnvio = ProgramarEnvio(view.)

    //recibo datos de fecha y hora
    @RequiresApi(Build.VERSION_CODES.O)
    fun save(date: String, time: String) {
        val dateTime = SimpleDateFormat("${Constants.DATE_FORMAT} ${Constants.TIME_FORMAT}").parse("$date $time")
        val response: Boolean = programarEnvio.invoke(dateTime.time)

        if (response) {
            view.show("proceso guardado")
            view.initActivity()
        }
    }
}