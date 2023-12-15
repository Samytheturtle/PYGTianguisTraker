package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ItemSold
import com.example.pygtianguistraker.data.model.ReservedProduct
class AdapterReservedProduct(
    private val context: Context,
    private val data: List<ReservedProduct>
) : RecyclerView.Adapter<AdapterReservedProduct.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewProduct: ImageView = view.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = view.findViewById(R.id.textViewProductName)
        val textViewProductPrice: TextView = view.findViewById(R.id.textViewProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.reserved_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        // Convertir Base64 a Bitmap y establecer en ImageView
        val imageBytes = Base64.decode(currentItem.fotoAnuncio, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.imageViewProduct.setImageBitmap(decodedImage)

        holder.textViewProductName.text = currentItem.nombreAnuncio
        holder.textViewProductPrice.text = "${currentItem.precioAnuncio}"
    }

    override fun getItemCount() = data.size
}
