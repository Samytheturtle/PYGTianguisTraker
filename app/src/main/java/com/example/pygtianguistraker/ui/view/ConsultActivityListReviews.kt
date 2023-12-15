package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.model.ReviewModel
import com.example.pygtianguistraker.data.network.ReviewApiClient
import com.example.pygtianguistraker.databinding.ConsultActivityListReviewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultActivityListReviews : AppCompatActivity() {
    private lateinit var binding: ConsultActivityListReviewsBinding
    private var id : Int = 0
    private lateinit var userType : String
    private lateinit var token : String
    private lateinit var authorizationHeader: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListReviewsBinding.inflate(layoutInflater)
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

        val reviewList = ArrayList<ReviewModel>()

        loadReview(id)

        val adapter = AdapterItem(this, reviewList)
        binding.recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReviews.adapter = adapter

    }

    private fun loadReview(idVendedor: Int) {
        val retrofit = Helper.getRetrofit()
        val apiInterface = retrofit.create(ReviewApiClient::class.java)

        apiInterface.getReview(authorizationHeader, idVendedor).enqueue(object : Callback<List<ReviewModel>> {
            override fun onResponse(call: Call<List<ReviewModel>>, response: Response<List<ReviewModel>>) {
                if (response.isSuccessful) {
                    val reviewList = response.body() ?: emptyList()
                    updateRecyclerView(reviewList)
                } else {
                    // Manejar respuesta no exitosa
                }
            }

            override fun onFailure(call: Call<List<ReviewModel>>, t: Throwable) {
                // Manejar el error
            }
        })
    }


    private fun updateRecyclerView(reviews: List<ReviewModel>) {
        val adapter = AdapterItem(this, ArrayList(reviews))
        binding.recyclerViewReviews.adapter = adapter
    }
}
