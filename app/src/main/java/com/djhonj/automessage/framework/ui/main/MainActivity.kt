package com.djhonj.automessage.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djhonj.automessage.databinding.ActivityMainBinding
import com.djhonj.automessage.framework.ui.programming.ProgrammingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNuevoEnvio.setOnClickListener {
            startActivity(Intent(this, ProgrammingActivity::class.java))
        }
    }
}