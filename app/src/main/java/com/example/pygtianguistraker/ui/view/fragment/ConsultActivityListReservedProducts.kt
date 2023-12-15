
package com.example.pygtianguistraker.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.pygtianguistraker.data.model.ReservedProduct
import com.example.pygtianguistraker.data.network.ReservedProductsApiClient
import com.example.pygtianguistraker.ui.view.AdapterReservedProduct
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultActivityListReservedProducts : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterReservedProduct
    private var id: Int = 0
    private lateinit var token: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_list_reserved_products, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewBookedProducts)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AdapterReservedProduct(requireContext(), arrayListOf())
        recyclerView.adapter = adapter

        val prefs = activity?.getSharedPreferences("my_app_information", Context.MODE_PRIVATE)
        val datosGuardados = prefs?.getString("datos_usuario", null)

        if (datosGuardados != null) {
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            token = "Bearer ${usuario.token}"
            id = usuario.id

            cargarProductosApartados()
        } else {
            // Manejar la ausencia de datos, por ejemplo, redirigir al usuario al inicio de sesión
        }

        return view
    }

    private fun cargarProductosApartados() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(ReservedProductsApiClient::class.java)

        service.getAdvertisementsPulledApartSeller(token, id).enqueue(object : Callback<List<ReservedProduct>> {
            override fun onResponse(call: Call<List<ReservedProduct>>, response: Response<List<ReservedProduct>>) {
                if (response.isSuccessful) {
                    val reservedProducts = response.body()?.map { apiProduct ->
                        ReservedProduct(
                            apiProduct.estatusAnuncio,
                            apiProduct.fotoAnuncio,
                            apiProduct.cantidadAnuncio,
                            apiProduct.precioAnuncio,
                            apiProduct.qrAnuncio,
                            apiProduct.nombreAnuncio,
                            apiProduct.idTianguisAnuncio,
                            apiProduct.idProductoAnuncio,
                            apiProduct.idVendedorAnuncio,
                            apiProduct.idCategoriaAnuncio
                        )
                    } ?: emptyList()

                    adapter = AdapterReservedProduct(requireContext(), ArrayList(reservedProducts))
                    recyclerView.adapter = adapter
                } else {
                    // Manejar respuesta no exitosa
                    Toast.makeText(context, "Error al cargar los productos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ReservedProduct>>, t: Throwable) {
                // Manejar fallo en la llamada
                Toast.makeText(context, "Error al cargar los productos: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
