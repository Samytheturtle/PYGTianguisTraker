package com.example.pygtianguistraker.data.model

import com.example.pygtianguistraker.R

data class ItemSold (
    val productImage: Int, // ID de recurso de la imagen del producto
    val productName: String,
    val soldPrice: String
)

fun pruebaItemSold() {
    val soldItemList = ArrayList<ItemSold>()
    soldItemList.add(ItemSold(R.drawable.buyer_image_placeholder, "Usuario1", "Excelente servicio. Recomendado."))
    soldItemList.add(ItemSold(R.drawable.buyer_image_placeholder, "Usuario2", "Muy buen vendedor. Producto de calidad."))

    // Uso de reviewList como datos estáticos para pruebas...
}