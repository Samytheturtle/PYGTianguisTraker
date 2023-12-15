package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.ReservedProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ReservedProductsApiClient {
    @GET("/api/advertisement/getAdvertisementsPulledApartSeller/{idVendedor}")
    fun getAdvertisementsPulledApartSeller(
        @Header("Authorization") token: String,
        @Path("idVendedor") idVendedor: Int):
            Call<List<ReservedProduct>>
}