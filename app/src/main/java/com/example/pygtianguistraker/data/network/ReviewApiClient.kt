package com.example.pygtianguistraker.data.network

import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.model.ReviewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ReviewApiClient {
    @POST("api/Buyer/addReview")
    fun addReview(
        @Header("Authorization") token: String,
        @Body reviewData: ReviewModel
    ): Call<ApiResponse>

    @GET("reviews")
    fun getReviews(): Call<List<ReviewItem>>
}