package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import android.widget.Button
import com.example.pygtianguistraker.databinding.ActivityModifyAdImageBinding
import com.example.pygtianguistraker.ui.view.commonFeatures.BaseAdImageActivity

class ModifyAdImageActivity : BaseAdImageActivity() {

    private lateinit var binding: ActivityModifyAdImageBinding

    private lateinit var btSaveModifyAd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyAdImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        imageView = binding.ivEditedImage
        buttonLoadImage = binding.btLoadImage
        btSaveModifyAd = binding.btSaveModifyAd
        buttonCancel = binding.btCancelModifyAdImage
    }

    override fun initListeners() {
        super.initListeners()

        btSaveModifyAd.setOnClickListener {
            //falta logica
        }
    }
}