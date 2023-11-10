package com.example.pygtianguistraker.data.model

data class UserSeller (var idVendedor : Int,
                       var nombreVendedor: String, var calificacionVendedor: String,
                       var correoVendedor: String, var contraseniaVendedor: String, var horarioLunesVendedor: String,
                       var horarioMartesVendedor: String, var horarioMiercolesVendedor:String, var horarioJuevesVendedor:String,
                       var horarioViernesVendedor: String, var horarioSabadoVeneddor: String, var horarioDomingoVendedor: String,
                       var idTianguisVendedor: Int, var fechaNacimientoVendedor: String){
    constructor() : this(
        0, "", "", "", "", "",
        "", "", "", "", "", "",
        0, ""
    )
}