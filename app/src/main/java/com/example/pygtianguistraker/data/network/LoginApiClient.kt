package com.example.pygtianguistraker.data.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiClient {
    @FormUrlEncoded
    @POST("/api/accsess/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Int> // Aquí asumo que la ID se devuelve como un entero, puedes ajustarlo según la respuesta real de tu API
}