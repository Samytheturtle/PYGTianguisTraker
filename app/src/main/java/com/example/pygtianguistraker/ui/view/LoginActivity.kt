package com.example.pygtianguistraker.ui.view
<<<<<<< HEAD
=======

>>>>>>> PYGTianguisTrajer-V-Beta-1.0
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.User
import com.example.pygtianguistraker.data.network.LoginApiClient
import com.example.pygtianguistraker.databinding.LoginActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.UserPopRegisterSelectFragment
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class LoginActivity : AppCompatActivity() {
    private var user = User()
    private lateinit var binding: LoginActivityBinding
    private lateinit var loginResponse: AuthResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySavedLanguage()
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD

        binding.Loginbutton.setOnClickListener{
=======
        binding.Loginbutton.setOnClickListener {
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
            createUserData()
            authVerification()
        }

        binding.registerButton.setOnClickListener {
            val dialogFragment = UserPopRegisterSelectFragment()
            dialogFragment.show(supportFragmentManager, "PopUp")
        }
    }
    private fun applySavedLanguage() {
        val preferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val language = preferences.getString("Language", "es") ?: "es"
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
    private fun sharePreferencesPYAndroid(loginResponse: AuthResponse) {
        // Nombre que identifica tus preferencias compartidas
        val preferenceName = "my_app_information"

        // Obtiene una instancia de SharedPreferences
        val prefs: SharedPreferences = getSharedPreferences(preferenceName, MODE_PRIVATE)

        // Obtiene un editor para realizar cambios en las preferencias compartidas
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val loginResponseJson = gson.toJson(loginResponse)
        Log.d("JSONDATA",loginResponseJson.toString())

        editor.putString("datos_usuario", loginResponseJson)
        editor.apply()
    }

    private fun applySavedLanguage() {
        val preferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val language = preferences.getString("Language", "es") ?: "es"
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun sharePreferencesPYAndroid(loginResponse: AuthResponse) {
        val preferenceName = "my_app_information"
        val prefs: SharedPreferences = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val loginResponseJson = gson.toJson(loginResponse)
        Log.d("JSONDATA", loginResponseJson.toString())
        editor.putString("datos_usuario", loginResponseJson)
        editor.apply()
    }

    private fun createUserData() {
        user.correoUsuario = binding.editTextTextEmailAddress.text.toString().trim()
        user.contraseniaUsuario = binding.editTextTextPassword.text.toString().trim()
    }

    private fun authVerification() {
        var validateRegister = false
        if (!validateFields()) {
            val retrofit = Helper.getRetrofit()
            val service = retrofit.create(LoginApiClient::class.java)
            val result: Call<AuthResponse> = service.loginUser(user)
            result.enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
<<<<<<< HEAD
                    if(response.isSuccessful){

                        loginResponse = response.body()!!
                        if(loginResponse.message.equals("Correo no encontrado")){
                            Toast.makeText(applicationContext, getString(R.string.ToastEmailIncorrect), Toast.LENGTH_SHORT).show()
                        }else if(loginResponse.message.equals("Contraseña incorrecta")){
                            Toast.makeText(applicationContext, getString(R.string.ToastPasswordIncorrect) ,Toast.LENGTH_SHORT).show()
                        } else{
                            sharePreferencesPYAndroid(loginResponse)
                            handleLoginSuccess()
                        }

=======
                    if (response.isSuccessful) {
                        loginResponse = response.body()!!
                        if (loginResponse.message.equals("Correo no encontrado")) {
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.ToastEmailIncorrect),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (loginResponse.message.equals("Contraseña incorrecta")) {
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.ToastPasswordIncorrect),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            sharePreferencesPYAndroid(loginResponse)
                            handleLoginSuccess()
                        }
                    } else {
                        when (response.code()) {
                            401 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Error de autorización",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            500 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Error del servidor",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Error desconocido",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
<<<<<<< HEAD
                    Toast.makeText(applicationContext, getString(R.string.ToastErrorResponse), Toast.LENGTH_SHORT).show()
=======
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.ToastErrorResponse),
                        Toast.LENGTH_SHORT
                    ).show()
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
                    println(t.message)
                }
            })
        }
    }

    private fun handleLoginSuccess() {
<<<<<<< HEAD

=======
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
        Toast.makeText(applicationContext, getString(R.string.ToastLoginCorrect), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }

    private fun validateFields(): Boolean {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.editTextTextEmailAddress.error = "Ingrese su correo electrónico"
        }

        if (password.isEmpty()) {
            binding.editTextTextPassword.error = "Ingrese su contraseña"
        }

        return email.isEmpty() || password.isEmpty()
    }
}
