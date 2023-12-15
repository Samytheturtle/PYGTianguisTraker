package com.example.pygtianguistraker.ui.view

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.ReviewModel
import com.example.pygtianguistraker.data.model.UserSeller
import com.example.pygtianguistraker.data.network.ReviewApiClient
import com.example.pygtianguistraker.databinding.CreateActivityReviewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateReviews : AppCompatActivity() {
    private lateinit var binding: CreateActivityReviewsBinding
    private var id : Int = 0
    private lateinit var userType : String
    private lateinit var token : String
    private lateinit var authorizationHeader: String
    private lateinit var listaVendedores: List<UserSeller>
    private var idVendedorSeleccionado: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("my_app_information", Context.MODE_PRIVATE)
        val datosGuardados = prefs?.getString("datos_usuario", null)

        if (datosGuardados != null) {
            // Los datos se encontraron en las preferencias compartidas
            // Haz algo con los datos recuperados

            // Por ejemplo, si estás usando Gson para analizar JSON en una clase de datos
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            authorizationHeader = usuario.token
            id = usuario.id
            userType = usuario.user

            // Realiza las operaciones que necesites con estos datos
        } else {
            // No se encontraron datos en las preferencias compartidas

        }


        cargarVendedores()

        binding.buttonSubmit.setOnClickListener {
            val idVendedor = idVendedorSeleccionado // Implementar esta función
            val score = binding.ratingBar.rating
            val review = binding.editTextReview.text.toString()

            if (idVendedorSeleccionado != -1 && score > 0 && review.isNotEmpty() ) {
                enviarReseña(idVendedorSeleccionado, score, review)
                Toast.makeText(
                    applicationContext,
                    getString(com.example.pygtianguistraker.R.string.ToastReviewSent),
                    Toast.LENGTH_SHORT
                ).show()
                limpiarCampos()
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(com.example.pygtianguistraker.R.string.ToastReviewIncorrect),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonCancel.setOnClickListener{
            finish()
        }
    }

    private fun limpiarCampos() {
        binding.ratingBar.rating = 0f // Establecer la calificación a su valor mínimo o inicial
        binding.editTextReview.setText("") // Limpiar el campo de texto de la reseña
    }
    private fun cargarVendedores() {
        val retrofit = Helper.getRetrofit()
        val apiInterface = retrofit.create(ReviewApiClient::class.java)

        apiInterface.getSellers().enqueue(object : Callback<List<UserSeller>> {
            override fun onResponse(call: Call<List<UserSeller>>, response: Response<List<UserSeller>>) {
                if (response.isSuccessful) {
                    listaVendedores = response.body() ?: emptyList()
                    poblarSpinnerConVendedores(listaVendedores)
                } else {
                    // Manejar el caso en que la respuesta no es exitosa
                }
            }

            override fun onFailure(call: Call<List<UserSeller>>, t: Throwable) {
                // Manejar el caso de fallo en la llamada a la API
            }
        })
    }

    private fun poblarSpinnerConVendedores(vendedores: List<UserSeller>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, vendedores.map { it.nombreVendedor })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSellerName.adapter = adapter

        binding.spinnerSellerName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val vendedorSeleccionado = listaVendedores[position]
                idVendedorSeleccionado = vendedorSeleccionado.idVendedor
                // Ahora idVendedorSeleccionado contiene el ID del vendedor seleccionado
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Opcionalmente manejar el caso en que nada es seleccionado
            }
        }
    }


    private fun enviarReseña(idVendedor: Int, calificacion: Float, reseña: String) {
        val retrofit = Helper.getRetrofit()
        val apiInterface = retrofit.create(ReviewApiClient::class.java)


        val reviewData = ReviewModel(idVendedor, calificacion, reseña)
        Log.d("as",reviewData.toString())
        apiInterface.addReview(authorizationHeader,reviewData).enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                // Manejar la respuesta exitosa
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar el error
            }
        })
    }


}