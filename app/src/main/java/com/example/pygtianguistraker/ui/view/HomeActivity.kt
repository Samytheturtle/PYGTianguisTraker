package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.databinding.HomeActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityFavProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityListReservedProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityProfile
import com.example.pygtianguistraker.ui.view.fragment.HometabfragmentAds
import com.example.pygtianguistraker.ui.view.fragment.SettingsFragment
import com.google.gson.Gson

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: HomeActivityBinding
    private lateinit var userType: String
    private lateinit var token: String
    private  var id:Int = 0
    public val preferenceName = "my_app_information"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HometabfragmentAds())


        val prefs = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val datosGuardados = prefs.getString("datos_usuario", null)

        if (datosGuardados != null) {
            Log.d("DATA",datosGuardados.toString())
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            token = usuario.token
            id = usuario.id
            userType = usuario.user

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
            }
            "Vendedor" -> {
                binding.bottomNavigationView.menu.findItem(R.id.favorite).isVisible = false
                binding.bottomNavigationView.menu.findItem(R.id.home).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.User).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.separates).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.settings).isVisible = true
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

