package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ModifyActivityAdsBinding

class ModifyActivityAds : AppCompatActivity() {
    private lateinit var binding: ModifyActivityAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}