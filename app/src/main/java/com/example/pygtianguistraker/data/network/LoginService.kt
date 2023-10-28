package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.network.LoginApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class LoginService {
    private val retrofit = Helper.getRetrofit()

    suspend fun loginUser(email: String, password: String): Int {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginApiClient::class.java).loginUser(email, password)
            response.body() ?:-1  // Puedes cambiar el valor predeterminado (-1) según tus necesidades
        }
    }
}