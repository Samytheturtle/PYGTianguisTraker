package com.example.pygtianguistraker.data.model

data class UserBuyer (var idComprador : Int,var correoUsuario: String, var contraseniaUsuario: String,
                       var nombreComprador: String, var ubicacionComprador: String, var fechaNacimientoComprador: String,
                       var idUsuarioComprador: Int){
    constructor() : this(
        0, "", "", "", "", "",0
    )
}