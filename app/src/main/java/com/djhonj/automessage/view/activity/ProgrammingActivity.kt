package com.djhonj.automessage.view.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.djhonj.automessage.databinding.ActivityProgrammingBinding
import com.djhonj.automessage.interfaces.IProgrammingPresenter
import com.djhonj.automessage.interfaces.IShowMessage
import com.djhonj.automessage.presenter.ProgrammingPresenter
import com.djhonj.automessage.view.fragment.DateDialogFragment
import com.djhonj.automessage.view.fragment.TimeDialogFragment
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class ProgrammingActivity : AppCompatActivity(), IShowMessage {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenterProgramming: IProgrammingPresenter = ProgrammingPresenter(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date()))
        binding.etHour.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(Date()))

        //mostrar datepicker dialog
        binding.editTextDate.setOnClickListener {
            val picker = DateDialogFragment { date -> onDateSelected(date) }
            picker.show(supportFragmentManager, "Seleccionar fecha")
        }

        binding.etHour.setOnClickListener {
            val picker = TimeDialogFragment { time -> onTimeSelected(time) }
            picker.show(supportFragmentManager, "Seleccionar hora")
        }

        binding.buttonSave.setOnClickListener {
            //envio datos de fecha y hora
            presenterProgramming.save(binding.etHour.text.toString())
        }
    }

    override fun show(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onDateSelected(date: String) {
        binding.editTextDate.setText(date)
    }

    private fun onTimeSelected(time: String) {
        binding.etHour.setText(time)
    }
}