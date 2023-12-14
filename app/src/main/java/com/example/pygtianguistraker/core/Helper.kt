package com.example.pygtianguistraker.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Helper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            //Cambien la ip cada que quieran ejecutarlo, la ip es la de sus computadoras.
<<<<<<< HEAD
            .baseUrl("http://192.168.100.74:3000")
=======
            .baseUrl("http://192.168.1.73:3000")
>>>>>>> PYGTianguisTrajer-V-Beta-1.0
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}