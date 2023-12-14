package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.ApiResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AdvertisementsApiClient {
    @POST("/api/advertisement/addAdvertisement")
    fun addAdvertisement(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<ApiResponse>

    @GET("/api/advertisement/getAdvertisements")
    fun getAllAds(): Call<List<AdsSeller>>

}