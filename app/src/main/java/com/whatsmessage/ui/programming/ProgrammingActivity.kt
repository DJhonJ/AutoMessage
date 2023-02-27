package com.whatsmessage.ui.programming

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.whatsmessage.R
import com.whatsmessage.ui.common.Constants
import com.whatsmessage.ui.common.IViewActivity
import com.whatsmessage.ui.common.ModalDialog
import com.whatsmessage.ui.contact.ContactActivity
import com.google.android.material.chip.Chip
import com.whatsmessage.databinding.ActivityProgrammingBinding
import com.whatsmessage.ui.common.Util
import com.whatsmessage.ui.main.MainActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class ProgrammingActivity: AppCompatActivity(), IViewActivity {
    private lateinit var binding: ActivityProgrammingBinding
    private val presenter: ProgrammingPresenter by inject { parametersOf(this) }
    private lateinit var dateSelectedServer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.title_navbar_programming)

        dateSelectedServer = Util.getCurrentDate(Constants.DATE_FORMAT)
        binding.etDate.setText(Util.getCurrentDate(Constants.DATE_FORMAT_USER))
        binding.etTime.setText(SimpleDateFormat(Constants.TIME_FORMAT, Locale("es")).format(Date()))

        binding.etDate.setOnClickListener {
            val picker = DateDialogFragment { dateUser, dateSelected -> onDateSelected(dateUser, dateSelected) }
            picker.show(supportFragmentManager, "select_date")
        }

        binding.etTime.setOnClickListener {
            val picker = TimeDialogFragment { time -> onTimeSelected(time) }
            picker.show(supportFragmentManager, "select_time")
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                presenter.save(
                    dateSelectedServer,
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
                    hint = it.phone
                    setOnCloseIconClickListener(chipClickListener)
                    isFocusable = false
                    isCheckedIconVisible = false
                }

                binding.chipGroup.addView(chip)
            }
        }
    }

    override fun onShowModalDialog(modalDialogFragment: ModalDialog, tag: String) {
        modalDialogFragment.show()
    }

    override fun onStartActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onDateSelected(date: String, dateSelected: String) {
        binding.etDate.setText(date)
        dateSelectedServer = dateSelected
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