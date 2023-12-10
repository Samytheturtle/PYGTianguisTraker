package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.UserBuyer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface  UserBuyerApiClient {
    @POST("/api/Buyer/registerBuyer")
    fun addBuyer(@Body buyer: UserBuyer): Call<UserBuyer>

}   