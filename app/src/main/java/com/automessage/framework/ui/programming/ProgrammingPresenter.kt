package com.automessage.framework.ui.programming

import android.os.Build
import androidx.annotation.RequiresApi
import com.automessage.domain.DateTime
import com.automessage.domain.InformationSend
import com.automessage.framework.ui.common.Constants
import com.automessage.usecases.ProgramarEnvio
import java.text.SimpleDateFormat

class ProgrammingPresenter(
    private val view: IProgrammingView,
    private val programarEnvio: ProgramarEnvio
    ) {
    //private val interactorProgrammin: IProgrammingInteractor = ProgrammingInteractor(this)
    //private val programarEnvio = ProgramarEnvio(view.)

    //recibo datos de fecha y hora
    @RequiresApi(Build.VERSION_CODES.O)
    fun save(date: String, time: String, numberSelected: String, message: String) {
        val dateTime = SimpleDateFormat("${Constants.DATE_FORMAT} ${Constants.TIME_FORMAT}").parse("$date $time")
        val response: Boolean = programarEnvio.invoke(dateTime.time, DateTime(date, time), InformationSend(numberSelected, message))

        if (response) {
            view.show("proceso guardado")
            view.initActivity()
        }
    }
}