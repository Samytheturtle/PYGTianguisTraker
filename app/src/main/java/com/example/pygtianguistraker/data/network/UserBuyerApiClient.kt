package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.UserBuyer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface  UserBuyerApiClient {
    @POST("/api/Buyer/registerBuyer")
    fun addBuyer(@Body buyer: UserBuyer): Call<UserBuyer>

    @GET("/api/buyer/getBuyer/{idBuyer}")
    fun getBuyer(@Header("Authorization") token: String, @Path("idBuyer") idBuyer: Int): Call<UserBuyer>
}   