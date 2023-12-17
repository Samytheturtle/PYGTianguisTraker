package com.example.pygtianguistraker.data.network


import com.example.pygtianguistraker.data.model.Tianguis
import com.example.pygtianguistraker.data.model.UserSeller
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


public interface   UserSellerApiClient {

    @GET("/api/tianguis")
    fun getTianguis(): Call<List<Tianguis>>


    @GET("/api/tianguis/getTianguisById/{idTianguis}")
    fun getOneTianguis(@Path("idTianguis") idTianguis: Int): Call<Tianguis>

    @POST("/api/Seller/registerSeller")
    fun addSeller(@Body seller: UserSeller): Call<UserSeller>

    @GET("/api/seller/getSeller/{idVendedor}")
    fun getSeller(@Header("Authorization") token: String, @Path("idVendedor") idVendedor:Int):Call<UserSeller>
}