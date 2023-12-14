package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
=======
import android.util.Log
import android.view.View
import android.widget.AdapterView
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
<<<<<<< HEAD
=======
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.Category
import com.example.pygtianguistraker.data.network.CategoryApiClient
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
import com.example.pygtianguistraker.databinding.ActivityCreateAdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAdBinding
    private lateinit var etNewName: EditText
    private lateinit var etNewPrice: EditText
    private lateinit var etNewQuantity: EditText
    private lateinit var etNewState: Spinner
    private lateinit var spNewType: Spinner
<<<<<<< HEAD


=======
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
    private lateinit var btNextCreateAdImage: Button
    private lateinit var btCancelCreateAd: Button

    private var categoryNames = ArrayList<String>()
    private var selectedCategoryId: Int = -1
    private var idListCategory: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        setupSpinners()
<<<<<<< HEAD
=======
        loadCategories()
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
        initListeners()

    }

    private fun initComponents() {
        etNewName = binding.etNewName
        etNewPrice = binding.etNewPrice
        etNewQuantity = binding.etNewQuantity
        etNewState = binding.etNewState
        etNewState.isEnabled = false
        spNewType = binding.spNewType
        btNextCreateAdImage = binding.btNextCreateAdImage
        btCancelCreateAd = binding.btCancelCreateAd
        categoryNames.add(0, "Selecciona una categoría")
    }

    private fun initListeners() {
        btNextCreateAdImage.setOnClickListener {
            val name = etNewName.text.toString().trim()
            val priceText = etNewPrice.text.toString().trim()
            val quantityText = etNewQuantity.text.toString().trim()
<<<<<<< HEAD
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

=======
            val state = etNewState.text.toString().trim()
            val type = spNewType.selectedItem.toString().trim()
            val idCategory = selectedCategoryId

            if (selectedCategoryId == -1) {
                Toast.makeText(applicationContext, "Por favor, selecciona una categoría válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() || state.isEmpty() || type == "Selecciona una categoría") {
                Toast.makeText(applicationContext, getString(R.string.ToastTextVoid), Toast.LENGTH_SHORT).show()
            } else {
                val price = priceText.toDoubleOrNull()
                val quantity = quantityText.toIntOrNull()

                if (price == null || quantity == null || price <= 0 || quantity <= 0) {
                    Toast.makeText(applicationContext, getString(R.string.ToastValidNumbers), Toast.LENGTH_SHORT).show()
                } else {
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
                    val intent = Intent(this, CreateAdImageActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("price", priceText)
                    intent.putExtra("quantity", quantityText)
<<<<<<< HEAD
                    intent.putExtra("state", State)
                    intent.putExtra("category", Type)

=======
                    intent.putExtra("state", state)
                    intent.putExtra("category", idCategory.toString())
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
                    startActivity(intent)
                    finish()
                }
            }
        }


        btCancelCreateAd.setOnClickListener {
            finish()
        }
    }
<<<<<<< HEAD
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
=======

    private fun setupSpinners() {
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNewType.adapter = categoryAdapter
        spNewType.setSelection(0)

        spNewType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                if (position > 0 && position <= idListCategory.size) {
                    selectedCategoryId = idListCategory[position - 1]
                } else {
                    selectedCategoryId = -1
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No es necesario hacer nada en este caso
            }
        }
    }

    private fun loadCategories() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(CategoryApiClient::class.java)
        val result: Call<List<Category>> = service.getCategorys()
        result.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    val listCategory = response.body() ?: emptyList()
                    for (category in listCategory) {
                        categoryNames.add(category.nombreCategoria)
                        idListCategory.add(category.idCategoria)
                    }
                    setupSpinners()
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("CreateAdActivity", "Error en la llamada: ${t.message}")
            }
        })
    }
}
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
