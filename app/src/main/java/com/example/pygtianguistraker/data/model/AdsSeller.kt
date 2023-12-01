package com.example.pygtianguistraker.data.model

data class AdsSeller(
    val imageResource: Int,
    val name: String,
    val price: String,
    val location: String

){
    constructor() : this(
        0,"","",""
    )
}
