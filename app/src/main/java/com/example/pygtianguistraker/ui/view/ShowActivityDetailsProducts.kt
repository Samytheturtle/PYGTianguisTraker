package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.ShowActivityDetailsProductsBinding

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.Adsproduct
import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.UpdateAdvertisementRequest
import com.example.pygtianguistraker.data.network.AdvertisementsApiClient
import com.example.pygtianguistraker.ui.view.ShowActivityDetailsProducts
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture
class ShowActivityDetailsProducts : AppCompatActivity() {
    private lateinit var binding: ShowActivityDetailsProductsBinding
    private var id:Int=0

    private lateinit var originalAdsList: AdsSeller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowActivityDetailsProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id",0)
        loadProducts()
        // Set the text directly using the binding object
        binding.tvProductName.text = "name"
        binding.tvPrice.text = "price"
        binding.tvTianguis.text = "location"
        binding.tvSeller.text = "category"
        binding.tvRating.text = "status"
        binding.tvstatus.text = "status"

        binding.btnReturn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso

            // Utiliza una Coroutine para agregar un retraso de 3 segundos antes de finalizar la actividad
            CoroutineScope(IO).launch {
                delay(3000) // Espera 3 segundos (3000 milisegundos)
                withContext(Main) {
                    finish() // Finaliza la actividad después del retraso
                }
            }
        }

        binding.btnReserve.setOnClickListener {
            reserveProduct(id)
        }
    }

    private fun reserveProduct(id: Int) {

    }
    private fun loadData() {
       // Tomar el primer elemento de la lista (puedes cambiar esto según tus necesidades)
            binding.tvProductName.text = "Nombre: ${originalAdsList.nombreAnuncio}"
            binding.tvPrice.text = "Precio: ${originalAdsList.precioAnuncio}$"
            binding.tvTianguis.text = "Tianguis: ${originalAdsList.idTianguisAnuncio}"
            binding.tvSeller.text = "Vendedor: ${originalAdsList.idVendedorAnuncio}"
            binding.tvstatus.text = "Estado: ${originalAdsList.estatusAnuncio}"

        val base64Image = originalAdsList.fotoAnuncio
        if (!base64Image.isNullOrEmpty()) {
            val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            binding.imageProductView.setImageBitmap(decodedBitmap)
        }
            // Otros elementos si es necesario
    }
    private fun loadProducts() {
        binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso

        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)
        val result: Call<AdsSeller> = service.getAdsById(id)
        result.enqueue(object : Callback<AdsSeller> {
            override fun onResponse(call: Call<AdsSeller>, response: Response<AdsSeller>) {
                if (response.isSuccessful) {
                    originalAdsList = response.body()!!
                    //Log.d("LOADHOME",originalAdsList.toString())
                    Toast.makeText(
                        applicationContext,
                        "Cargando, espere un momento por favor",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Utiliza una Coroutine para agregar un retraso de 3 segundos después de recibir la respuesta
                    CoroutineScope(IO).launch {
                        delay(5000) // Espera 3 segundos (3000 milisegundos)
                        withContext(Main) {
                            binding.progressBar.visibility = View.GONE // Oculta la ProgressBar después del retraso
                            loadData()
                        }
                    }


                } else {

                }
            }
            override fun onFailure(call: Call<AdsSeller>, t: Throwable) {
                Log.e("loadProducts", "Failure")
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Fallo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
