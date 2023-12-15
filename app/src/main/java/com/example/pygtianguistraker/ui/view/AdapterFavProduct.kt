package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.ui.view.adapter.ViewHolderFavProducts

class AdapterFavProduct(
    private val context: Context,
    private val data: ArrayList<FavProduct>
) : RecyclerView.Adapter<ViewHolderFavProducts>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavProducts {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_product, parent, false)
        return ViewHolderFavProducts(view)
    }


    override fun onBindViewHolder(holder: ViewHolderFavProducts, position: Int) {
        val currentItem = data[position]
        holder.imageViewProduct.setImageResource(currentItem.imageResource)
        holder.textViewProductName.text = currentItem.name
        holder.textViewPrice.text = currentItem.price
        holder.textViewLocation.text = currentItem.location
    }

    override fun getItemCount() = data.size
}
