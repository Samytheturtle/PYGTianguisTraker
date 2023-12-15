package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.network.AdvertisementsApiClient
import com.example.pygtianguistraker.databinding.HomeActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityFavProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityListReservedProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityProfile
import com.example.pygtianguistraker.ui.view.fragment.HometabfragmentAds
import com.example.pygtianguistraker.ui.view.fragment.SettingsFragment
import com.example.pygtianguistraker.ui.viewModel.AdsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: HomeActivityBinding
    private lateinit var userType: String
    private lateinit var token: String
    private  var id:Int = 0
    private lateinit var originalAdsList: List<AdsSeller>
    private lateinit var adsViewModel: AdsViewModel
    public val preferenceName = "my_app_information"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adsViewModel = ViewModelProvider(this).get(AdsViewModel::class.java)

        //setFragment(HometabfragmentAds())


        val prefs = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val datosGuardados = prefs.getString("datos_usuario", null)

        if (datosGuardados != null) {
            Log.d("DATA",datosGuardados.toString())
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            token = usuario.token
            id = usuario.id
            userType = usuario.user
            Log.d("datos_usuario", token)
            Log.d("datos_usuario", id.toString())
            Log.d("datos_usuario", userType)
        } else {
            Toast.makeText(applicationContext, "A ocurrido un error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }
        restoreData()
        when (userType) {
            "Comprador" -> {
                binding.bottomNavigationView.menu.findItem(R.id.favorite).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.home).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.User).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.separates).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.settings).isVisible = true
                loadProducts()
            }
            "Vendedor" -> {
                binding.bottomNavigationView.menu.findItem(R.id.favorite).isVisible = false
                binding.bottomNavigationView.menu.findItem(R.id.home).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.User).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.separates).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.settings).isVisible = true
                loadProductsVendedor()
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setFragment(HometabfragmentAds())
                R.id.favorite -> setFragment(ConsultActivityFavProducts())
                R.id.separates -> setFragment(ConsultActivityListReservedProducts())
                R.id.settings -> setFragment(SettingsFragment())
                R.id.User -> setFragment(ConsultActivityProfile())
                else -> { }
            }
            true
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Delega el manejo del resultado a los fragmentos
        supportFragmentManager.fragments.forEach { fragment ->
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        // Borrar las preferencias compartidas al cerrar la aplicación
        clearSharedPreferences()
    }
    private fun loadProducts() {
        Log.d("LOADHOME","1")
        binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso

        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)
        val result: Call<List<AdsSeller>> = service.getAllAds()
        Log.d("LOADHOME","2")
        result.enqueue(object : Callback<List<AdsSeller>> {
            override fun onResponse(call: Call<List<AdsSeller>>, response: Response<List<AdsSeller>>) {
                if (response.isSuccessful) {

                    adsViewModel.originalAdsList = response.body() ?: emptyList()
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
                            setFragment(HometabfragmentAds())
                        }
                    }


                } else {

                }
            }

            override fun onFailure(call: Call<List<AdsSeller>>, t: Throwable) {
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
    private fun loadProductsVendedor() {
        binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso

        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)
        val result: Call<List<AdsSeller>> = service.getAllAdsSeller(id)
        result.enqueue(object : Callback<List<AdsSeller>> {
            override fun onResponse(call: Call<List<AdsSeller>>, response: Response<List<AdsSeller>>) {
                if (response.isSuccessful) {
                    adsViewModel.originalAdsList = response.body() ?: emptyList()

                    Toast.makeText(
                        applicationContext,
                        "Cargando tus productos :D, espere un momento por favor",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Utiliza una Coroutine para agregar un retraso de 3 segundos después de recibir la respuesta
                    CoroutineScope(IO).launch {
                        delay(5000) // Espera 3 segundos (3000 milisegundos)
                        withContext(Main) {
                            binding.progressBar.visibility = View.GONE // Oculta la ProgressBar después del retraso
                            setFragment(HometabfragmentAds())
                        }
                    }


                } else {

                }
            }

            override fun onFailure(call: Call<List<AdsSeller>>, t: Throwable) {
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
    private fun restoreData() {
        val prefs = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val datosGuardados = prefs.getString("datos_usuario", null)

        if (datosGuardados != null) {
            // Los datos se encontraron en las preferencias compartidas
            // Haz algo con los datos recuperados
        } else {
            // No se encontraron datos en las preferencias compartidas
        }
    }

    private fun clearSharedPreferences() {
        val prefs = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val editor = prefs.edit()

        // Borra los datos guardados con la clave "datos_usuario"
        editor.remove("datos_usuario")

        // Aplica los cambios
        editor.apply()
    }
    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutSeller, fragment)
        fragmentTransaction.commit()
    }



}

