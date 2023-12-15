package com.example.pygtianguistraker.data.model

import retrofit2.Call
import retrofit2.http.GET

interface Seller {
    @GET("/api/seller/getSellers")
    fun getSellers(): Call<List<Seller>>
}