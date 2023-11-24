package com.example.pygtianguistraker.data.model

import com.example.pygtianguistraker.R

data class ReviewItem(
    val buyerImage: Int, // ID de recurso de la imagen del comprador
    val buyerName: String,
    val reviewContent: String
)


fun pruebaReviewItem() {
    val reviewList = ArrayList<ReviewItem>()
    reviewList.add(ReviewItem(R.drawable.buyer_image_placeholder, "Usuario1", "Excelente servicio. Recomendado."))
    reviewList.add(ReviewItem(R.drawable.buyer_image_placeholder, "Usuario2", "Muy buen vendedor. Producto de calidad."))

    // Uso de reviewList como datos estáticos para pruebas...
}