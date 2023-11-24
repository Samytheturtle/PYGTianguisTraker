package com.example.pygtianguistraker.data.model

data class UserLogin (var correoUsuario : String,
                      var contraseniaUsuario: String,){
    constructor() : this(
        "",
        ""
    )
}