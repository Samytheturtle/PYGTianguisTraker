package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.network.FavProductsApiClient
import com.example.pygtianguistraker.ui.view.adapter.ViewHolderFavProducts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterFavProduct(
    private val context: Context,
    private val data: ArrayList<FavProduct>
) : RecyclerView.Adapter<AdapterFavProduct.ViewHolderFavProducts>() {

    class ViewHolderFavProducts(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewProduct: ImageView = view.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = view.findViewById(R.id.textViewProductName)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
        // Asumiendo que no hay botón de reservar en este punto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavProducts {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_product, parent, false)
        return ViewHolderFavProducts(view)
    }

    override fun onBindViewHolder(holder: ViewHolderFavProducts, position: Int) {
        val currentItem = data[position]

        if (currentItem.fotoAnuncio.isNotEmpty()) {
            val imageBytes = Base64.decode(currentItem.fotoAnuncio, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            holder.imageViewProduct.setImageBitmap(decodedImage)
        } else {
            holder.imageViewProduct.setImageResource(R.drawable.without_image)
        }

        holder.textViewProductName.text = currentItem.nombreAnuncio
        holder.textViewPrice.text = "$${currentItem.precioAnuncio}"
    }

    override fun getItemCount() = data.size
}
