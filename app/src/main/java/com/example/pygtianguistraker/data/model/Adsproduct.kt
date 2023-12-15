package com.example.pygtianguistraker.data.model

import java.io.Serializable

<<<<<<< HEAD
data class Adsproduct (var idAnuncio: Int,var estatusAnuncio: String, var precioAnuncio: Float, var fotoAnuncio:String,
                      var qrAnuncio: String, var nombreAnuncio: String, var TianguisAnuncio: String,
                      var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var CategoriaAnuncio: String):
    Serializable {
    constructor() : this(0,"", 0F, "",
=======

data class Adsproduct (var estatusAnuncio: String, var precioAnuncio: Float, var fotoAnuncio:String,
                       var qrAnuncio: String, var nombreAnuncio: String, var TianguisAnuncio: String,
                       var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var CategoriaAnuncio: String):
    Serializable {
    constructor() : this("", 0F, "",
>>>>>>> 74ca342 (Lista de productos reservados)
        "", "","",0,0,""
    )
}