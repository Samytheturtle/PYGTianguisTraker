package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ModifyActivityProfileSellerBinding

class ModifyActivityProfileSeller : AppCompatActivity() {
    private lateinit var binding: ModifyActivityProfileSellerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyActivityProfileSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}