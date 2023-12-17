package com.example.pygtianguistraker.data.model

import java.io.Serializable


data class Adsproduct (var idAnuncio: Int,var estatusAnuncio: String, var precioAnuncio: Float, var fotoAnuncio:String,
                      var qrAnuncio: String, var nombreAnuncio: String, var TianguisAnuncio: String,
                      var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var CategoriaAnuncio: String):
    Serializable {
    constructor() : this(0,"", 0F, "","", "","",0,0,"")}
