package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityPublishedAdsBuyerBinding

class ConsultActivityPublishedAdsbuyer : AppCompatActivity() {
    private lateinit var binding: ConsultActivityPublishedAdsBuyerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityPublishedAdsBuyerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}