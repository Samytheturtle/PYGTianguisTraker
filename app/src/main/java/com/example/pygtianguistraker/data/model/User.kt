package com.example.pygtianguistraker.data.model

data class User (var correoUsuario: String,
                 var contraseniaUsuario: String){
    constructor() : this(
        "","" 
    )
}