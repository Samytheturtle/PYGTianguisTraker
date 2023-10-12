package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ConsultActivityListReviewsBinding

class ConsultActivityListReviews : AppCompatActivity() {
    private lateinit var binding: ConsultActivityListReviewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}