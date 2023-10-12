package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ChangeActivityLenguageBinding

class ChangeActivityLenguage : AppCompatActivity() {
    private lateinit var binding: ChangeActivityLenguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangeActivityLenguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}