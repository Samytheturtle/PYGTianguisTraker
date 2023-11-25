package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
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
        reviewList.add(ReviewItem(R.drawable.user_icon, "Usuario1", "Excelente servicio. Recomendado."))
        reviewList.add(ReviewItem(R.drawable.user_icon, "Usuario2", "Muy buen vendedor. Producto de calidad."))

        val adapter = AdapterItem(this, reviewList)
        val listView: ListView = findViewById(R.id.listViewReviews)
        listView.adapter = adapter
    }
}
