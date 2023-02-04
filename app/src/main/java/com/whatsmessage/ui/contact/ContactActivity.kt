package com.whatsmessage.ui.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.whatsmessage.R
import com.whatsmessage.databinding.ActivityContactBinding
import com.whatsmessage.domain.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ContactActivity : AppCompatActivity(), IContactView {
    private lateinit var binding: ActivityContactBinding

    private val presenter: ContactPresenter by inject ()
    private lateinit var numberSelected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        loadContacts()
    }

    override fun onClickItem(contact: Contact) {
        setResult(RESULT_OK, Intent().apply {
            putExtra("contact", contact)
        })

        finish()
    }

   private fun loadContacts() {
        lifecycleScope.launch (Dispatchers.Main) {
            binding.recycler.adapter = ContactsAdapter(presenter.getContacts(), this@ContactActivity)
        }
    }
}