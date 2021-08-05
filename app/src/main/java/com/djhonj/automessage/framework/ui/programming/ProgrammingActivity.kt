package com.djhonj.automessage.framework.ui.programming

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.djhonj.automessage.databinding.ActivityProgrammingBinding
import com.djhonj.automessage.domain.InformationSend
import com.djhonj.automessage.framework.ui.common.Constants
import com.djhonj.automessage.framework.ui.common.IShowMessage
import com.djhonj.automessage.framework.ui.contact.ContactActivity
import com.djhonj.automessage.framework.ui.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ProgrammingActivity: AppCompatActivity(), IProgrammingView {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenter: ProgrammingPresenter by inject { parametersOf(this) }
    private lateinit var numberPhone: String

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
            val picker = DateDialogFragment { date ->
                    onDateSelected(date)
                }
            picker.show(supportFragmentManager, "Seleccionar fecha")
        }

        binding.etTime.setOnClickListener {
            val picker = TimeDialogFragment { time ->
                    onTimeSelected(time)
                }
            picker.show(supportFragmentManager, "Seleccionar hora")
        }

        binding.buttonGoWhatsapp.setOnClickListener {
            val intentWhatsapp = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("whatsapp://send?phone=${numberPhone}&text=${binding.etMensaje.text}")
                `package` = "com.whatsapp"
            }

            startActivity(intentWhatsapp)
        }

        binding.buttonSave.setOnClickListener {
            //envio datos de fecha y hora
            presenter.save(binding.etDate.text.toString(), binding.etTime.text.toString(), numberPhone, binding.etMensaje.text.toString())
        }

        binding.buttonAddContact.setOnClickListener {
            startActivityForResult(Intent(this, ContactActivity::class.java), 0)
        }

        binding.buttonEnabledAccesibility.setOnClickListener {
            //habilitar accesibilidad
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (Activity.RESULT_OK == resultCode) {
            numberPhone = data?.getStringExtra("number").toString()

            show("Contact selected")
        }
    }

    override fun show(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun initActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun onDateSelected(date: String) {
        binding.etDate.setText(date)
    }

    private fun onTimeSelected(time: String) {
        binding.etTime.setText(time)
    }
}