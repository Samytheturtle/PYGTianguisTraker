package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryApiClient {
    @GET("/api/advertisement/advertisement/categorys ")
    fun getCategorys(): Call<List<Category>>
}