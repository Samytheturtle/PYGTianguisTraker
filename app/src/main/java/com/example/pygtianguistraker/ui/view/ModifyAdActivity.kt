package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ActivityModifyAdBinding

class ModifyAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModifyAdBinding

    private lateinit var etEditedName: EditText
    private lateinit var etEditedPrice: EditText
    private lateinit var etEditQuantity: EditText
    private lateinit var etEditedState: EditText
    private lateinit var spEditedType: Spinner

    private lateinit var btNextModifyAdImage: Button
    private lateinit var btCancelModifyAd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        etEditedName = binding.etEditedName
        etEditedPrice = binding.etEditedPrice
        etEditQuantity = binding.etEditedQuantity
        etEditedState = binding.etEditedState
        spEditedType = binding.spEditedType
        btNextModifyAdImage = binding.btNextModifyAdImage
        btCancelModifyAd = binding.btCancelModifyAd
    }

    private fun initListeners() {

        btNextModifyAdImage.setOnClickListener {
            //falta logica
            intent = Intent(this, ModifyAdImageActivity::class.java)
            startActivity(intent)
        }

        btCancelModifyAd.setOnClickListener {
            finish()
        }
    }
}