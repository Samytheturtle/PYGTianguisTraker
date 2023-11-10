package com.example.pygtianguistraker.ui.viewModel.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract

class CaptureImageContract: ActivityResultContract<Unit, Bitmap?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bitmap? {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            val data = intent.getParcelableExtra<Bitmap>("data")

            if (data != null) {

                return data
            }
        }
        return null
    }

}