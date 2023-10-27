package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.databinding.RegisterActivityTianguisBinding

class RegisterUserActivityTianguis : AppCompatActivity() {

    private lateinit var binding:RegisterActivityTianguisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityTianguisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btRegisterSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}