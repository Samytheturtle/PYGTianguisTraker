package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityPublishedAdsSellerBinding

class ConsultActivityPublishedAdsSeller : AppCompatActivity(){
    private lateinit var binding: ConsultActivityPublishedAdsSellerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityPublishedAdsSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}