package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.ReviewItem
import com.example.pygtianguistraker.databinding.ConsultActivityListReviewsBinding

class ConsultActivityListReviews : AppCompatActivity() {
    private lateinit var binding: ConsultActivityListReviewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConsultActivityListReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reviewList = ArrayList<ReviewItem>()
        reviewList.add(ReviewItem(R.drawable.user_icon, "Usuario", "Excelente servicio. Recomendado."))
        reviewList.add(ReviewItem(R.drawable.user_icon, "Usuario", "Muy buen vendedor. Producto de calidad."))

        val adapter = AdapterItem(this, reviewList)
        binding.recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReviews.adapter = adapter

    }
}
