package com.djhonj.automessage.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djhonj.automessage.databinding.ActivityProgrammingBinding
import com.djhonj.automessage.interfaces.IProgrammingPresenter
import com.djhonj.automessage.interfaces.IShowMessage
import com.djhonj.automessage.presenter.ProgrammingPresenter
import com.djhonj.automessage.view.fragment.DateDialogFragment
import com.djhonj.automessage.view.fragment.TimeDialogFragment

class ProgrammingActivity : AppCompatActivity(), IShowMessage {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenterProgramming: IProgrammingPresenter = ProgrammingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            presenterProgramming.save()
        }
    }

    override fun show(string: String) {

    }

    private fun onDateSelected(date: String) {
        binding.editTextDate.setText(date)
    }

    private fun onTimeSelected(time: String) {
        binding.etHour.setText(time)
    }
}