package com.example.pygtianguistraker.data.network


import com.example.pygtianguistraker.data.model.Tianguis
import com.example.pygtianguistraker.data.model.UserSeller
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


public interface UserSellerApiClient {

    @GET("/api/tianguis")
    fun getTianguis(): Call<List<Tianguis>>

    @POST("/api/register/Seller")
    fun addSeller(@Body seller: UserSeller): Call<UserSeller>

}