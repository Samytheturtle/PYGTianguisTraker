package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.model.ReviewModel
import com.example.pygtianguistraker.data.model.UserSeller
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApiClient {
    @POST("api/Buyer/addReview")
    fun addReview(
        @Header("Authorization") token: String,
        @Body reviewData: ReviewModel
    ): Call<ApiResponse>

    @GET("/api/seller/getReview/{idVendedor}")
    fun getReview(
        @Header("Authorization") token: String,
        @Path("idVendedor") idVendedor: Int):
            Call<List<ReviewModel>>

    @GET("api/seller/getSellers")
    fun getSellers(): Call<List<UserSeller>>
}