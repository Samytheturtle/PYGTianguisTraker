package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityFavProductsBinding

class ConsultActivityFavProducts : AppCompatActivity() {
    private lateinit var binding: ConsultActivityFavProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityFavProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}