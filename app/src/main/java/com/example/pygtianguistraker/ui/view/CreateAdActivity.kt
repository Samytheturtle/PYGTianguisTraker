package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.Category
import com.example.pygtianguistraker.data.network.CategoryApiClient
import com.example.pygtianguistraker.databinding.ActivityCreateAdBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAdBinding
    private lateinit var etNewName: EditText
    private lateinit var etNewPrice: EditText
    private lateinit var etNewQuantity: EditText
    private lateinit var etNewState: EditText
    private lateinit var spNewType: Spinner
    private lateinit var btNextCreateAdImage: Button
    private lateinit var btCancelCreateAd: Button

    private lateinit var userType: String
    private lateinit var authorizationHeader: String
    private var id: Int = 0

    private var categoryNames = ArrayList<String>()
    private var selectedCategoryId: Int = -1
    private var idListCategory: ArrayList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("my_app_information", Context.MODE_PRIVATE)
        val datosGuardados = prefs.getString("datos_usuario", null)

        if (datosGuardados != null) {
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            authorizationHeader = usuario.token
            id = usuario.id
            userType = usuario.user
            Log.d("CREATEDATA:!", id.toString())
            Log.d("CREATEDATA:!", authorizationHeader.toString())
            Log.d("CREATEDATA:!", userType.toString())
        } else {
            Toast.makeText(applicationContext, "Error desconocido", Toast.LENGTH_SHORT).show()
        }



        initComponents()
        setupSpinners()
        loadCategories()
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
            val state = etNewState.text.toString().trim()
            val type = spNewType.selectedItem.toString().trim()
            val idCategory = selectedCategoryId
            val userType = userType
            val authorizationHeader = authorizationHeader
            val id = id
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
                    val intent = Intent(this, CreateAdImageActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("price", priceText)
                    intent.putExtra("quantity", quantityText)
                    intent.putExtra("state", state)
                    intent.putExtra("category", idCategory.toString())
                    intent.putExtra("user", userType)
                    intent.putExtra("token",authorizationHeader)
                    intent.putExtra("idV",id.toString())

                    startActivity(intent)
                    finish()
                }
            }
        }

        btCancelCreateAd.setOnClickListener {
            //adsViewModel.originalAdsList=null
            finish()

        }
    }

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


