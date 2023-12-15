package com.example.pygtianguistraker.data.model

import java.io.Serializable


data class Adsproduct (var estatusAnuncio: String, var precioAnuncio: Float, var fotoAnuncio:String,
                       var qrAnuncio: String, var nombreAnuncio: String, var TianguisAnuncio: String,
                       var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var CategoriaAnuncio: String):
    Serializable {
    constructor() : this("", 0F, "",
        "", "","",0,0,""

    )
}