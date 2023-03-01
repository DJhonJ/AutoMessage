package com.whatsmessage.ui.contact

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.whatsmessage.R
import com.whatsmessage.databinding.ActivityContactBinding
import com.whatsmessage.domain.Contact
import com.whatsmessage.ui.common.IActivityView
import com.whatsmessage.ui.component.ModalDialog
import com.whatsmessage.ui.component.PermissionsRequest
import com.whatsmessage.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ContactActivity : AppCompatActivity(), IContactView, IActivityView {
    private lateinit var binding: ActivityContactBinding
    private val permissionsRequest: PermissionsRequest by inject { parametersOf(this) }
    private val presenter: ContactPresenter by inject ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.title_navbar_contact)
    }

    override fun onResume() {
        super.onResume()

        if (permissionsRequest.checkPermissions()) {
            loadContacts()
        } else {
            onStartActivity(Intent(this, MainActivity::class.java).apply {
                Intent.FLAG_ACTIVITY_CLEAR_TOP
                Intent.FLAG_ACTIVITY_NO_HISTORY
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onClickItem(contact: Contact) {
        setResult(RESULT_OK, Intent().apply {
            putExtra("contact", contact)
        })

        finish()
    }

    override fun getInstance(): Activity {
        return this
    }

    override fun onStartActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun onShowMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun onShowModalDialog(modalDialog: ModalDialog, tag: String) {
        TODO("Not yet implemented")
    }

    private fun loadContacts() {
        lifecycleScope.launch (Dispatchers.Main) {
            binding.recycler.adapter = ContactsAdapter(presenter.getContacts(), this@ContactActivity)
        }
    }
}