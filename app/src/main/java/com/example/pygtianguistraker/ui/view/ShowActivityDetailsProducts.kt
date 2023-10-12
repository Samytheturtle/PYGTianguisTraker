package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ShowActivityDetailsProductsBinding

class ShowActivityDetailsProducts : AppCompatActivity() {
    private lateinit var binding: ShowActivityDetailsProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowActivityDetailsProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}