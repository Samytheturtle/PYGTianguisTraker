package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.databinding.ActivityCreateAdBinding


class CreateAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAdBinding

    private lateinit var etNewName: EditText
    private lateinit var etNewPrice: EditText
    private lateinit var etNewQuantity: EditText
    private lateinit var etNewState: Spinner
    private lateinit var spNewType: Spinner


    private lateinit var btNextCreateAdImage: Button
    private lateinit var btCancelCreateAd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        setupSpinners()
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
            val name = etNewName.text.toString().trim()
            val priceText = etNewPrice.text.toString().trim()
            val quantityText = etNewQuantity.text.toString().trim()
            val State = etNewState.selectedItem.toString().trim()
            val Type= spNewType.selectedItem.toString().trim()

            // Validar campos vacíos
            if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() || State.isEmpty() || Type.isEmpty() ) {
                Toast.makeText(applicationContext, getString(R.string.ToastTextVoid), Toast.LENGTH_SHORT).show()
            } else {
                // Parsea el precio y la cantidad a números
                val price = priceText.toDoubleOrNull()
                val quantity = quantityText.toIntOrNull()

                // Verificar si el precio y la cantidad son números positivos
                if (price == null || quantity == null || price <= 0 || quantity <= 0) {
                    Toast.makeText(applicationContext, getString(R.string.ToastValidNumbers), Toast.LENGTH_SHORT).show()
                } else {

                    val intent = Intent(this, CreateAdImageActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("price", priceText)
                    intent.putExtra("quantity", quantityText)
                    intent.putExtra("state", State)
                    intent.putExtra("category", Type)

                    startActivity(intent)
                    finish()
                }
            }
        }


        btCancelCreateAd.setOnClickListener {
            finish()
        }
    }
    private fun setupSpinners() {
        var states = arrayOf("Nuevo", "Usado", "Reacondicionado", "Mal estado")
        var categories = arrayOf("Categoría 1", "Categoría 2", "Categoría 3")
        // Configura el Adapter para los Spinner
        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, states)
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        etNewState.adapter = stateAdapter

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNewType.adapter = categoryAdapter
    }

    }