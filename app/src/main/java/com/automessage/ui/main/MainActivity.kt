package com.automessage.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.automessage.databinding.ActivityMainBinding
import com.automessage.ui.common.ModalDialog
import com.automessage.ui.programming.ProgrammingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNuevoEnvio.setOnClickListener {
            val intent = Intent(this, ProgrammingActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        //presenter.onVerifyAccessibility()
    }

    fun onShowModalDialog(modalDialogFragment: ModalDialog) {
        modalDialogFragment.show()
    }
}