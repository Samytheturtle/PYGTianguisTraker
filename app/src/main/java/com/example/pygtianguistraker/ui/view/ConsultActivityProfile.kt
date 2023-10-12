package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityProfileBinding

class ConsultActivityProfile : AppCompatActivity() {
    private lateinit var binding: ConsultActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}