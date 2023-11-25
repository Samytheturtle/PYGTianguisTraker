package com.example.pygtianguistraker.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.ui.view.AdapterFavProduct


class ConsultActivityFavProducts : Fragment(){
    private lateinit var listView: ListView
    private lateinit var adapter: AdapterFavProduct
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_fav_products, container, false)
        listView = view.findViewById(R.id.listViewFavoriteProducts)


        val favProducts = ArrayList<FavProduct>()
        favProducts.add(FavProduct(R.drawable.without_image, "Camisa naruto", "$50","Tianguis Revolución"))
        favProducts.add(FavProduct(R.drawable.without_image, "Control Xbox 360", "$200","Tianguis Revolución"))
        favProducts.add(FavProduct(R.drawable.without_image, "Tenis Air Force 1", "$750","Tianguis Revolución"))
        adapter = AdapterFavProduct(requireContext(), favProducts)
        listView.adapter = adapter

        return view
    }
}