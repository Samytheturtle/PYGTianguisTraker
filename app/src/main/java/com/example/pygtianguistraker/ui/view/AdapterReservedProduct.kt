package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ItemSold
import com.example.pygtianguistraker.data.model.ReservedProduct

class AdapterReservedProduct (context: Context, private val data: ArrayList<ReservedProduct>) :
    ArrayAdapter<ReservedProduct>(context, R.layout.reserved_product, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val holder: ViewHolder

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.reserved_product, parent, false)
            holder = ViewHolder()

            // Asignar vistas al ViewHolder
            holder.imageViewProduct = view.findViewById(R.id.imageViewProduct)
            holder.textViewProductName = view.findViewById(R.id.textViewProductName)
            holder.textViewProductPrice = view.findViewById(R.id.textViewProductPrice)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val currentItem = data[position]

        // Asignar valores a las vistas
        holder.imageViewProduct?.setImageResource(currentItem.productImage)
        holder.textViewProductName?.text = currentItem.productName
        holder.textViewProductPrice?.text = currentItem.productPrice
        holder.textViewSellingPlace?.text = currentItem.sellingPlace

        return view!!
    }

    // Clase ViewHolder para mantener las referencias a las vistas
    private class ViewHolder {
        var imageViewProduct: ImageView? = null
        var textViewProductName: TextView? = null
        var textViewProductPrice: TextView? = null
        var textViewSellingPlace: TextView? = null

    }
}