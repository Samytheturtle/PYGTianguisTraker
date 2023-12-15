package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.data.model.ItemSold
import com.example.pygtianguistraker.databinding.ConsultActivityListSellProductsBinding

class ConsultActivityListSellProducts: AppCompatActivity() {
    private lateinit var binding: ConsultActivityListSellProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListSellProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val soldProductsList = ArrayList<ItemSold>()
        soldProductsList.add(ItemSold(R.drawable.without_image, "Muñeco Jefe Maestro", "$1000."))
        soldProductsList.add(ItemSold(R.drawable.without_image, "Halo Infinite", "600."))

        val adapter = AdapterSoldItem(this, soldProductsList)
        val listView: ListView = findViewById(R.id.recyclerViewProductsSold)
        listView.adapter = adapter
    }
}