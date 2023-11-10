package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import android.widget.Button
import com.example.pygtianguistraker.databinding.ActivityCreateAdImageBinding
import com.example.pygtianguistraker.ui.view.commonFeatures.BaseAdImageActivity

class CreateAdImageActivity : BaseAdImageActivity() {

    private lateinit var binding: ActivityCreateAdImageBinding

    private lateinit var btSaveCreateAd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        imageView = binding.ivNewImage
        buttonLoadImage = binding.btLoadImage
        buttonCancel = binding.btCancelCreateAdImage
        btSaveCreateAd = binding.btSaveCreateAd
    }

    override fun initListeners() {
        super.initListeners()

        btSaveCreateAd.setOnClickListener {
            //falta logica
        }
    }
}