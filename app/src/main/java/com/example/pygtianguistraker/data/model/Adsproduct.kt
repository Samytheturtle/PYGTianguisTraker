package com.example.pygtianguistraker.data.model

data class Adsproduct (var estatusAnuncio: String, var precioAnuncio: Float,
                      var qrAnuncio: String, var nombreAnuncio: String, var idTianguisAnuncio: Int,
                      var cantidadAnuncio: Int,var idVendedorAnuncio: Int, var idCategoriaAnuncio: Int){
    constructor() : this("", 0F, "",
        "", 0,0,0,0
    )
}