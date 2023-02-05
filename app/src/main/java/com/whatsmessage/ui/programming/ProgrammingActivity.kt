package com.whatsmessage.ui.programming

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
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
import com.whatsmessage.ui.main.MainActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.title_navbar_programming)

        binding.etDate.setText(SimpleDateFormat(Constants.DATE_FORMAT).format(Date()))
        binding.etTime.setText(SimpleDateFormat(Constants.TIME_FORMAT).format(Date()))

        binding.etDate.setOnClickListener {
            val picker = DateDialogFragment { date -> onDateSelected(date) }
            picker.show(supportFragmentManager, "select_date")
        }

        binding.etTime.setOnClickListener {
            val picker = TimeDialogFragment { time -> onTimeSelected(time) }
            picker.show(supportFragmentManager, "select_time")
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                presenter.save(
                    binding.etDate.text.toString(),
                    binding.etTime.text.toString(),
                    binding.etMessage.text.toString())
            }
        }

        binding.btnAddContact.setOnClickListener {
            startActivityForResult(Intent(this, ContactActivity::class.java), 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        val intent = Intent(this, MainActivity::class.java).apply {
            Intent.FLAG_ACTIVITY_CLEAR_TOP
            Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        onStartActivity(intent)

        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (Activity.RESULT_OK == resultCode) {
            val contactAdded = presenter.addContact(data?.getSerializableExtra("contact"))

            contactAdded?.let {
                val chip = Chip(this).apply {
                    text = it.name
                    hint = it.number
                    setOnCloseIconClickListener(chipClickListener)
                    isFocusable = false
                    isCheckedIconVisible = false
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

    private val chipClickListener = View.OnClickListener {
        val chip = it as Chip

        binding.chipGroup.removeView(chip)
        presenter.removeContact(chip.hint.toString())
    }
}