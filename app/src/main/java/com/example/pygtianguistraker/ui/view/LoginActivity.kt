package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.User
import com.example.pygtianguistraker.data.network.LoginApiClient
import com.example.pygtianguistraker.databinding.LoginActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.UserPopRegisterSelectFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var user= User()
    private lateinit var binding: LoginActivityBinding
    private lateinit var loginResponse: AuthResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Loginbutton.setOnClickListener{
            //createUserData()
              //  authVerification() !! SE QUITÓ PARA PRUEBAS
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener{
            val dialogFragment = UserPopRegisterSelectFragment()
            dialogFragment.show(supportFragmentManager, "PopUp")
        }
    }

    private fun createUserData() {
        user.correoUsuario = binding.editTextTextEmailAddress.text.toString().trim()
        user.contraseniaUsuario=binding.editTextTextPassword.text.toString().trim()
    }

    private fun authVerification(){
        var validateRegister:Boolean=false
        if(!validateFields()){
            val retrofit = Helper.getRetrofit()
            val service =retrofit.create(LoginApiClient::class.java)
            val result: Call<AuthResponse> = service.loginUser(user)
            result.enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if(response.isSuccessful){
                        loginResponse = response.body()!!
                        handleLoginSuccess()
                        Log.d("Prueba","Revision de ejecucion ${validateRegister}")
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "A ocurrido un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    println(t.message)
                }

            })

        }
    }

    private fun handleLoginSuccess() {
        Log.d("Prueba 2 ", "Revision de ejecución true")
        Toast.makeText(applicationContext, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validateFields() :Boolean{
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            // El campo de correo electrónico está vacío
            binding.editTextTextEmailAddress.error = "Ingrese su correo electrónico"

        }

        if (password.isEmpty()) {
            // El campo de contraseña está vacío
            binding.editTextTextPassword.error = "Ingrese su contraseña"

        }
        if(email.isEmpty() || password.isEmpty()){
            return true
        }
            return false
    }
}