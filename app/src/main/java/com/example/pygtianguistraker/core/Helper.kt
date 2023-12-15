package com.example.pygtianguistraker.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Helper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            //Cambien la ip cada que quieran ejecutarlo, la ip es la de sus computadoras.
            .baseUrl("http://localhost:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}