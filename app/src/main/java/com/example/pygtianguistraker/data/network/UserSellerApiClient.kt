package com.example.pygtianguistraker.data.network


import com.example.pygtianguistraker.data.model.TianguisCollection
import retrofit2.Call
import retrofit2.http.GET


public interface UserSellerApiClient {

    @GET("/api/tianguis")
    fun getTianguis(): Call<List<TianguisCollection>>

}