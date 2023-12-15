package com.example.pygtianguistraker.ui.view.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R

class ViewHolderFavProducts(view: View) : RecyclerView.ViewHolder(view) {

        val imageViewProduct: ImageView = view.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = view.findViewById(R.id.textViewProductName)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
        val buttonReserve: Button = view.findViewById(R.id.buttonReserve)
}