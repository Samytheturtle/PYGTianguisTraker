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
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.model.ReviewModel

class AdapterItem(
    private val context: Context,
    private val data: ArrayList<ReviewModel>
) : RecyclerView.Adapter<AdapterItem.ViewHolder>() {

    // Define ViewHolder here
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBuyer: ImageView = view.findViewById(R.id.imageViewBuyer)
        val textViewBuyerName: TextView = view.findViewById(R.id.textViewBuyerName)
        val textViewReviewContent: TextView = view.findViewById(R.id.textViewReviewContent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.seller_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imageViewBuyer.setImageResource(R.drawable.buyer_image_placeholder) // Asegúrate de que buyerImage sea un ID de drawable
        holder.textViewBuyerName.text = "User"
        holder.textViewReviewContent.text = item.mensajeResenia
    }


    override fun getItemCount(): Int {
        return data.size
    }
}
