package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.CreateActivityAdsBinding

class CreateActivityAds : AppCompatActivity() {
    private lateinit var binding: CreateActivityAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}