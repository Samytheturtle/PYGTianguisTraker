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
class AdapterItem(
    private val context: Context,
    private val data: ArrayList<ReviewItem>
) : RecyclerView.Adapter<AdapterItem.ViewHolder>() {

    // Define ViewHolder here
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Tus componentes, como ImageView y TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.seller_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // Configura tu holder aquí
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
