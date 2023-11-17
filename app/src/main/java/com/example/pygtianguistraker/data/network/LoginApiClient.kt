package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiClient {
    @POST("/api/access/")
    fun loginUser(@Body user: User): Call<AuthResponse>
}