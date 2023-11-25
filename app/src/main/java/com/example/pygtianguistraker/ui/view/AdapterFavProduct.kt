package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.data.model.ReviewItem

class AdapterFavProduct (context: Context, private val data: ArrayList<FavProduct>) :
    ArrayAdapter<FavProduct>(context, R.layout.item_fav_product, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val holder: ViewHolder

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_fav_product, parent, false)
            holder = ViewHolder()

            // Asignar vistas al ViewHolder
            holder.imageViewProduct = view.findViewById(R.id.imageViewProduct)
            holder.textViewProductName = view.findViewById(R.id.textViewProductName)
            holder.textViewPrice = view.findViewById(R.id.textViewPrice)
            holder.textViewLocation = view.findViewById(R.id.textViewLocation)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val currentItem = data[position]

        // Asignar valores a las vistas
        holder.imageViewProduct?.setImageResource(currentItem.imageResource)
        holder.textViewProductName?.text = currentItem.name
        holder.textViewPrice?.text = currentItem.price
        holder.textViewLocation?.text = currentItem.location
        return view!!
    }

    // Clase ViewHolder para mantener las referencias a las vistas
    private class ViewHolder {
        var imageViewProduct: ImageView? = null
        var textViewProductName: TextView? = null
        var textViewPrice: TextView? = null
        var textViewLocation: TextView? = null
    }
}



