package com.example.pygtianguistraker.ui.view.commonFeatures

import android.graphics.Bitmap
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.ui.interfaces.OnImageCapturedListener
import com.example.pygtianguistraker.ui.interfaces.OnImageUriListener
import com.example.pygtianguistraker.ui.view.fragment.OptionsLoadImageFragment

open class BaseAdImageActivity : AppCompatActivity(), OnImageCapturedListener, OnImageUriListener {

    protected lateinit var imageView: ImageView
    protected lateinit var buttonLoadImage: Button
    protected lateinit var buttonCancel: Button

    protected open fun initListeners() {
        buttonLoadImage.setOnClickListener {
            showOptionsDialog()
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun showOptionsDialog() {
        val optionsLoadImageFragment = OptionsLoadImageFragment()
        optionsLoadImageFragment.setOnImageCapturedListener(this)
        optionsLoadImageFragment.setImagesUriListener(this)
        supportFragmentManager.let {
            optionsLoadImageFragment.show(it, "OptionsLoadImageDialog")
        }
    }

    override fun onImageCaptured(imageBitmap: Bitmap) {
        imageView.setImageBitmap(imageBitmap)
    }

    override fun onImagesUri(imageUri: Uri) {
        imageView.setImageURI(imageUri)
    }

}