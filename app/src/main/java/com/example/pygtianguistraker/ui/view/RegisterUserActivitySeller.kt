package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.RegisterActivitySellerBinding

class RegisterUserActivitySeller : AppCompatActivity(){
    private lateinit var binding:RegisterActivitySellerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivitySellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNextSeller.setOnClickListener{
            val intent= Intent(this, RegisterUserActivityTianguis::class.java)
            startActivity(intent)
        }

        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }

}