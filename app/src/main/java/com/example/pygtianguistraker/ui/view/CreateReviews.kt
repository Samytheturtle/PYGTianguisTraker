package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.CreateActivityReviewsBinding

class CreateReviews : AppCompatActivity() {
    private lateinit var binding: CreateActivityReviewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}