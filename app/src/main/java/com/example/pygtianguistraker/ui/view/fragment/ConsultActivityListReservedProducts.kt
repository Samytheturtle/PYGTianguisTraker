
package com.example.pygtianguistraker.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ReservedProduct
import com.example.pygtianguistraker.ui.view.AdapterReservedProduct

class ConsultActivityListReservedProducts : Fragment(){
    private lateinit var listView: ListView
    private lateinit var adapter: AdapterReservedProduct
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_list_reserved_products, container, false)
        listView = view.findViewById(R.id.listViewBookedProducts)


        val products = ArrayList<ReservedProduct>()
        products.add(ReservedProduct(R.drawable.buyer_image_placeholder, "Wii U", "$250","Tianguis Revolución"))
        products.add(ReservedProduct(R.drawable.buyer_image_placeholder, "Xbox 360", "$800","Tianguis Revolución"))

        adapter = AdapterReservedProduct(requireContext(), products)
        listView.adapter = adapter

        return view
    }
}