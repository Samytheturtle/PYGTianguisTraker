package com.example.pygtianguistraker.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.data.network.FavProductsApiClient
import com.example.pygtianguistraker.ui.view.AdapterFavProduct
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConsultActivityFavProducts : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterFavProduct
    private var id: Int = 0
    private lateinit var token: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_fav_products, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewFavoriteProducts)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AdapterFavProduct(requireContext(), arrayListOf())
        recyclerView.adapter = adapter

        val prefs = activity?.getSharedPreferences("my_app_information", Context.MODE_PRIVATE)
        val datosGuardados = prefs?.getString("datos_usuario", null)

        if (datosGuardados != null) {
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            token = "Bearer ${usuario.token}"
            id = usuario.id

            cargarProductosFavoritos()
        } else {
            // Manejar la ausencia de datos, por ejemplo, redirigir al usuario al inicio de sesión
            // O mostrar un mensaje indicando que es necesario iniciar sesión.
        }

        return view
    }

    private fun cargarProductosFavoritos() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(FavProductsApiClient::class.java)

        service.getAdvertisementsPulledApartSeller(token, id).enqueue(object : Callback<List<FavProduct>> {
            override fun onResponse(call: Call<List<FavProduct>>, response: Response<List<FavProduct>>) {
                if (response.isSuccessful) {
                    val favProducts = response.body() ?: emptyList()
                    adapter = AdapterFavProduct(requireContext(), ArrayList(favProducts))
                    recyclerView.adapter = adapter
                } else {
                    // Manejar respuesta no exitosa
                    Toast.makeText(context, "Error al cargar los productos: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<FavProduct>>, t: Throwable) {
                // Manejar fallo en la llamada
                Toast.makeText(context, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
