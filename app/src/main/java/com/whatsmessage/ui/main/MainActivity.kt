package com.whatsmessage.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.whatsmessage.R
import com.whatsmessage.databinding.ActivityMainBinding
import com.whatsmessage.domain.Message
import com.whatsmessage.ui.programming.ProgrammingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewDispatch.setOnClickListener {
            val intent = Intent(this, ProgrammingActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavView.setOnNavigationItemSelectedListener { item ->
            val state: Int = when(item.itemId) {
                R.id.page_sent -> 1
                R.id.page_not_sent -> 2
                R.id.page_cancel -> 3
                else -> 0
            }

            loadMessages(state)

            true
        }
    }

    override fun onResume() {
        super.onResume()
        loadMessages(0)
    }

    private fun loadMessages(state: Int) {
        lifecycleScope.launch (Dispatchers.Main) {
            val messages = presenter.getListMessage(state)
            var fragment: Fragment? = MessagesFragment(MessagesAdapter(messages))

            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }
}