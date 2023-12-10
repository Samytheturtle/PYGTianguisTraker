package com.example.pygtianguistraker.data.model

import java.io.Serializable

data class AuthResponse(
    val message: String,
    val token: String,
    val id: Int,
    val user:String
): Serializable
