package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ModifyActivityProfileBinding

class ModifyActivityProfile : AppCompatActivity() {
    private lateinit var binding: ModifyActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}