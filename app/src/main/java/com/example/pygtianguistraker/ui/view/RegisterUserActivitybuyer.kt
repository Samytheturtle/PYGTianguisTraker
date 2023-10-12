package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.RegisterActivityBuyerBinding

class RegisterUserActivitybuyer  : AppCompatActivity(){
    private lateinit var binding: RegisterActivityBuyerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBuyerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}