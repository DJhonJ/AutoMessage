package com.automessage.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.automessage.databinding.ActivityMainBinding
import com.automessage.ui.common.ModalDialog
import com.automessage.ui.programming.ProgrammingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.lang.Exception

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
    }

    override fun onResume() {
        super.onResume()
        loadRecycler()
    }

    private fun loadRecycler() {
        val state = 0

        lifecycleScope.launch(Dispatchers.Main) {
            val list = presenter.getListMessage(state)
            val adapter = MessagesAdapter(list)
            binding.recyclerMessage.adapter = adapter // = adapter
        }
    }
}