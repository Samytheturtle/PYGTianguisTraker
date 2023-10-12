package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityListSellProductsBinding

class ConsultActivityListSellProducts: AppCompatActivity() {
    private lateinit var binding: ConsultActivityListSellProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListSellProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}