package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.UserBuyer
import com.example.pygtianguistraker.data.model.UserSeller
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface  UserBuyerApiClient {
    @POST("/api/register/buyer")
    fun addBuyer(@Body buyer: UserBuyer): Call<UserBuyer>

}   