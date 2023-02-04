package com.whatsmessage.ui.programming

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.whatsmessage.R
import com.whatsmessage.databinding.ActivityProgrammingBinding
import com.whatsmessage.domain.Contact
import com.whatsmessage.ui.common.Constants
import com.whatsmessage.ui.common.IViewActivity
import com.whatsmessage.ui.common.ModalDialog
import com.whatsmessage.ui.contact.ContactActivity
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class ProgrammingActivity: AppCompatActivity(), IViewActivity {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenter: ProgrammingPresenter by inject { parametersOf(this) }
    private val contactsSelected: MutableList<Contact>? by lazy { mutableListOf() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.etDate.setText(SimpleDateFormat(Constants.DATE_FORMAT).format(Date()))
        binding.etTime.setText(SimpleDateFormat(Constants.TIME_FORMAT).format(Date()))
        //binding.etTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(Date()))

        binding.etDate.setOnClickListener {
            if (presenter.onVerifyAccessibility(this)) {
                val picker = DateDialogFragment { date -> onDateSelected(date) }
                picker.show(supportFragmentManager, "select_date")
            }
        }

        binding.etTime.setOnClickListener {
            if (presenter.onVerifyAccessibility(this)) {
                val picker = TimeDialogFragment { time -> onTimeSelected(time) }
                picker.show(supportFragmentManager, "select_time")
            }
        }

        binding.btnSave.setOnClickListener {
            if (presenter.onVerifyAccessibility(this)) {
                lifecycleScope.launch(Dispatchers.Main) {
                    presenter.onSave(
                        binding.etDate.text.toString(),
                        binding.etTime.text.toString(),
                        contactsSelected,
                        binding.etMessage.text.toString())
                }
            }
        }

        binding.btnAddContact.setOnClickListener {
            if (presenter.onVerifyAccessibility(this)) {
                startActivityForResult(Intent(this, ContactActivity::class.java), 0)
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        presenter.onVerifyAccessibility(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (Activity.RESULT_OK == resultCode) {
            val newContact = presenter.onAssignContact(data?.getSerializableExtra("contact"))

            if (newContact != null) {
                contactsSelected?.add(newContact)

                val chip = Chip(this).apply {
                    text = newContact!!.name
                }

                binding.chipGroup.addView(chip)
            }
        }
    }

    override fun onShowModalDialog(modalDialog: ModalDialog, tag: String) {
        modalDialog.show()
    }

    override fun onStartActivity(intent: Intent) {
        startActivity(intent)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onDateSelected(date: String) {
        binding.etDate.setText(date)
    }

    private fun onTimeSelected(time: String) {
        binding.etTime.setText(time)
    }
}