package com.example.pygtianguistraker.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Loginbutton.setOnClickListener{

            val intent= Intent(this, ConsultActivityPublishedAdsbuyer::class.java)
            startActivity(intent)
        }
    }
}