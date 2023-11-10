package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ActivityCreateAdBinding

class CreateAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAdBinding

    private lateinit var etNewName: EditText
    private lateinit var etNewPrice: EditText
    private lateinit var etNewQuantity: EditText
    private lateinit var etNewState: EditText
    private lateinit var spNewType: Spinner

    private lateinit var btNextCreateAdImage: Button
    private lateinit var btCancelCreateAd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        etNewName = binding.etNewName
        etNewPrice = binding.etNewPrice
        etNewQuantity = binding.etNewQuantity
        etNewState = binding.etNewState
        spNewType = binding.spNewType
        btNextCreateAdImage = binding.btNextCreateAdImage
        btCancelCreateAd = binding.btCancelCreateAd
    }

    private fun initListeners() {

        btNextCreateAdImage.setOnClickListener {
            //falta logica
            intent = Intent(this, CreateAdImageActivity::class.java)
            startActivity(intent)
        }

        btCancelCreateAd.setOnClickListener {
            finish()
        }
    }
}