package com.automessage.framework.ui.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.automessage.R
import com.automessage.domain.Contact
import com.automessage.framework.ui.programming.ProgrammingActivity
import org.koin.android.ext.android.inject

class ContactActivity : AppCompatActivity(), IContactView {
    private val presenter: ContactPresenter by inject ()
    private lateinit var numberSelected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val recycler = this.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = ContactsAdapter(presenter.getContacts(), this)
    }

    override fun onClickItem(number: String) {
        numberSelected = number

        setResult(RESULT_OK, Intent().apply {
            putExtra("number", numberSelected)
        })

        finish()
    }
}