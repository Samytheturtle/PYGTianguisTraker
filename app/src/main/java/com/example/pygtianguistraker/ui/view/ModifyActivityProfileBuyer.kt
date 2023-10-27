package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ModifyActivityProfileBuyerBinding

class ModifyActivityProfileBuyer: AppCompatActivity() {
    private lateinit var binding: ModifyActivityProfileBuyerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyActivityProfileBuyerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}