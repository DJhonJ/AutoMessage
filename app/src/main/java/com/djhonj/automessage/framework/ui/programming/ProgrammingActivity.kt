package com.djhonj.automessage.framework.ui.programming

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.djhonj.automessage.databinding.ActivityProgrammingBinding
import com.djhonj.automessage.framework.ui.common.Constants
import com.djhonj.automessage.framework.ui.common.IShowMessage
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ProgrammingActivity: AppCompatActivity(), IShowMessage {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenter: ProgrammingPresenter by inject { parametersOf(this) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etDate.setText(SimpleDateFormat(Constants.DATE_FORMAT).format(Date()))
        binding.etTime.setText(SimpleDateFormat(Constants.TIME_FORMAT).format(Date()))
        //binding.etTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(Date()))

        //mostrar datepicker dialog
        binding.etDate.setOnClickListener {
            val picker =
                DateDialogFragment { date ->
                    onDateSelected(date)
                }
            picker.show(supportFragmentManager, "Seleccionar fecha")
        }

        binding.etTime.setOnClickListener {
            val picker =
                TimeDialogFragment { time ->
                    onTimeSelected(time)
                }
            picker.show(supportFragmentManager, "Seleccionar hora")
        }

        binding.buttonSave.setOnClickListener {
            //envio datos de fecha y hora
            presenter.save(binding.etDate.text.toString(), binding.etTime.text.toString())
        }
    }

    override fun show(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onDateSelected(date: String) {
        binding.etDate.setText(date)
    }

    private fun onTimeSelected(time: String) {
        binding.etTime.setText(time)
    }
}