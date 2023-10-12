package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ShowDetailsSellerBinding

class ShowDetailsSeller : AppCompatActivity() {
    private lateinit var binding: ShowDetailsSellerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowDetailsSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}