package com.example.pygtianguistraker.ui.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.pygtianguistraker.databinding.DialogOptionsLoadImageBinding
import com.example.pygtianguistraker.ui.interfaces.OnImageCapturedListener
import com.example.pygtianguistraker.ui.interfaces.OnImageUriListener
import com.example.pygtianguistraker.ui.viewModel.contracts.CaptureImageContract

class OptionsLoadImageFragment : DialogFragment() {

    private var binding: DialogOptionsLoadImageBinding? = null

    private var imageCapturedListener: OnImageCapturedListener? = null
    private var imageUriListener: OnImageUriListener? = null

    private val activityContext: Context = requireContext()
    private val activity: FragmentActivity = requireActivity()

    private var btTakePhoto: Button? = null
    private var btLoadFromGallery: Button? = null
    private var btCancelAdImage: Button? = null

    private lateinit var captureImageLauncher: ActivityResultLauncher<Unit>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogOptionsLoadImageBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        activityResult()
        initListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnImageCapturedListener) {
            imageCapturedListener = context
        }
        if (context is OnImageUriListener) {
            imageUriListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    private fun initComponents() {
        btTakePhoto = binding?.btTakePhoto
        btLoadFromGallery = binding?.btLoadFromGallery
        btCancelAdImage = binding?.btCancelAdImage
    }

    private fun activityResult() {
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {

                val data: Intent? = it.data

                if (data != null) {

                    val imageUri: Uri? = data.data

                    if (imageUri != null) {

                        imageUriListener?.onImagesUri(imageUri)

                    } else {

                        Toast.makeText(activityContext, "No se ha seleccionado una imágen.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        captureImageLauncher = registerForActivityResult(CaptureImageContract()) {

            if (it != null) {

                imageCapturedListener?.onImageCaptured(it)

            } else {

                Toast.makeText(activityContext, "No se ha seleccionado una imágen.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListeners(){
        btTakePhoto?.setOnClickListener {
            validateAccessToCamera()
            dismiss()
        }

        btLoadFromGallery?.setOnClickListener {
            validateAccessToGallery()
            dismiss()
        }

        btCancelAdImage?.setOnClickListener {
            dismiss()
        }
    }

    private fun validateAccessToCamera() {
        val cameraPermission: String = Manifest.permission.CAMERA
        val storagePermission: String = Manifest.permission.WRITE_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(activityContext, cameraPermission)
            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activityContext,
                    storagePermission) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, arrayOf(cameraPermission, storagePermission),
                REQUEST_PERMISSION_CODE_GALLERY
            )

        } else {

            openCamera()
        }
    }

    private fun openCamera() {
        captureImageLauncher.launch(null)
    }

    private fun validateAccessToGallery() {
        val storagePermission: String = Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(activityContext, storagePermission)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, arrayOf(storagePermission),
                REQUEST_PERMISSION_CODE_CAMERA
            )

        } else {

            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        galleryLauncher.launch(Intent.createChooser(intent, "Seleccionar imágen"))
    }

    fun setOnImageCapturedListener(listener: OnImageCapturedListener) {
        imageCapturedListener = listener
    }

    fun setImagesUriListener(listener: OnImageUriListener) {
        imageUriListener = listener
    }

    companion object {
        private const val REQUEST_PERMISSION_CODE_CAMERA = 1000
        private const val REQUEST_PERMISSION_CODE_GALLERY = 2000
    }

}