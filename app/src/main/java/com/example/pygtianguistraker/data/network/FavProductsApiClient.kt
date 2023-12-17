package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.FavProduct

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FavProductsApiClient {
    @GET("/api/advertisement/getFavoritesAdvertisement/{idComprador}")
    fun getAdvertisementsPulledApartSeller(
        @Header("Authorization") token: String,
        @Path("idComprador") idComprador: Int):
            Call<List<FavProduct>>

}