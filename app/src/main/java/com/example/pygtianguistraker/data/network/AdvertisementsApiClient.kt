package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.ApiResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AdvertisementsApiClient {
    @POST("/api/advertisement/addAdvertisement")
    fun addAdvertisement(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<ApiResponse>

    @GET("/api/advertisement/getAdvertisements")
    fun getAllAds(): Call<List<AdsSeller>>

<<<<<<< HEAD
    @GET("/api/advertisement/getAdvertisementBySeller/{idVendedor}")
    fun getAllAdsSeller( @Path("idVendedor") idVendedor:Int): Call<List<AdsSeller>>

    @PUT("/api/advertisement/updateAdvertisementSelled")
    fun updateAdsSeller(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<ApiResponse>

    @PUT("/api/advertisement/updateAdvertisementAvaible")
    fun updateAdsSellerAvaible(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<ApiResponse>

    @GET("/api/advertisement/getAdvertisement/{idAnuncio}")
    fun getAdsById( @Path("idAnuncio") idAnuncio:Int): Call<AdsSeller>

    @PUT("/api/advertisement/addPulletApartAdvertisement")
    fun addPulletApartAdvertisement(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): Call<ApiResponse>
=======
>>>>>>> 74ca342 (Lista de productos reservados)
}