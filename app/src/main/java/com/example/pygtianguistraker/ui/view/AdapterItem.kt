package com.example.pygtianguistraker.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ReviewItem
class AdapterItem(context: Context, private val data: ArrayList<ReviewItem>) :
    ArrayAdapter<ReviewItem>(context, R.layout.seller_review, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val holder: ViewHolder

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.seller_review, parent, false)
            holder = ViewHolder()

            // Asignar vistas al ViewHolder
            holder.imageViewBuyer = view.findViewById(R.id.imageViewBuyer)
            holder.textViewBuyerName = view.findViewById(R.id.textViewBuyerName)
            holder.textViewReviewContent = view.findViewById(R.id.textViewReviewContent)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val currentItem = data[position]

        // Asignar valores a las vistas
        holder.imageViewBuyer?.setImageResource(currentItem.buyerImage)
        holder.textViewBuyerName?.text = currentItem.buyerName
        holder.textViewReviewContent?.text = currentItem.reviewContent

        return view!!
    }

    // Clase ViewHolder para mantener las referencias a las vistas
    private class ViewHolder {
        var imageViewBuyer: ImageView? = null
        var textViewBuyerName: TextView? = null
        var textViewReviewContent: TextView? = null
    }
}



