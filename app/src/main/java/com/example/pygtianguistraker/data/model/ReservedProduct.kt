package com.example.pygtianguistraker.data.model

import com.example.pygtianguistraker.R

data class ReservedProduct(val productImage: Int, // ID de recurso de la imagen del producto
                           val productName: String,
                           val productPrice: String,
                           val sellingPlace: String)
fun pruebaReservedProduct() {
    val reserverList = ArrayList<ReservedProduct>()
    reserverList.add(ReservedProduct(R.drawable.buyer_image_placeholder, "Wii U", "$250","Tianguis Revolución"))
    reserverList.add(ReservedProduct(R.drawable.buyer_image_placeholder, "Xbox 360", "$800","Tianguis Revolución"))

    // Uso de reviewList como datos estáticos para pruebas...
}