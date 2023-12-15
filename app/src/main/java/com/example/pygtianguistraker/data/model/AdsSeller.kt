package com.example.pygtianguistraker.data.model
import java.io.Serializable
data class AdsSeller(

    var idAnuncio: Int,
    var estatusAnuncio: String, var fotoAnuncio:String, var precioAnuncio: Float,
    var qrAnuncio: String, var nombreAnuncio: String, var idTianguisAnuncio: Int,
    var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var idCategoriaAnuncio: Int
): Serializable{
    constructor() : this(0, "", "", 0F, "",
        "",0,0,0,0)
}