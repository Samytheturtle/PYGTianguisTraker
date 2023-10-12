package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityListReservedProductsBinding

class ConsultActivityListReservedProducts : AppCompatActivity() {
    private lateinit var binding: ConsultActivityListReservedProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListReservedProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}