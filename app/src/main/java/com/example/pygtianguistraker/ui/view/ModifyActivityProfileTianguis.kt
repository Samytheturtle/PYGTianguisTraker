package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ModifyActivityProfileTianguisBinding

class ModifyActivityProfileTianguis: AppCompatActivity() {
    private lateinit var binding: ModifyActivityProfileTianguisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModifyActivityProfileTianguisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}