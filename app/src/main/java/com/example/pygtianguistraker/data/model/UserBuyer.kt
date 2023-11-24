package com.example.pygtianguistraker.data.model

data class UserBuyer (var idComprador : Int,
                       var nombreComprador: String, var ubicacionComprador: String,
                       var correoUsuario: String, var contraseniaUsuario: String, var horarioLunesVendedor: String,
                       var idUsuarioComprador: Int){
    constructor() : this(
        0, "", "", "", "", "",0
    )
}