package com.example.pygtianguistraker.ui.view.fragment

import AdsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.AdsSeller

class HometabfragmentAds : Fragment() {
    private lateinit var listView: ListView
    private lateinit var adapter: AdsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_published_ads, container, false)
        listView = view.findViewById(R.id.listViewAdsHome)


        val Adssellproduct = ArrayList<AdsSeller>()
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Camisa naruto", "$50","Tianguis Revolución"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Control Xbox 360", "$200","Tianguis Revolución"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Tenis Air Force 1", "$750","Tianguis Revolución"))
        adapter = AdsAdapter(requireContext(), Adssellproduct)
        listView.adapter = adapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}