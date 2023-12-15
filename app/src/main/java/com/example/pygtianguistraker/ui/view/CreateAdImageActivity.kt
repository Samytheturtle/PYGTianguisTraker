package com.example.pygtianguistraker.ui.view

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.UserSeller
import com.example.pygtianguistraker.data.network.AdvertisementsApiClient
import com.example.pygtianguistraker.data.network.UserSellerApiClient
import com.example.pygtianguistraker.databinding.ActivityCreateAdImageBinding
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreateAdImageActivity : AppCompatActivity() {

    private lateinit var buttonCancel: Button
    private lateinit var buttonLoadImage: Button
    private lateinit var imageView: ImageView
    private lateinit var binding: ActivityCreateAdImageBinding
    private var ads = AdsSeller()
    private lateinit var btSaveCreateAd: Button
    private lateinit var responseAuth: ApiResponse
    private lateinit var Usersellerresponse: UserSeller

    private lateinit var loginResponse: AuthResponse

    private lateinit var user:String
    private lateinit var token:String
    private var id:Int = 0


    private var selectedImageUri: Uri? = null

    private val IMAGE_PICK_REQUEST = 101

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAdImageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initComponents()
        loadDataIntent()
        Log.d("DATA:!",token)
        Log.d("DATA:!",id.toString())
        Log.d("DATA:!",user)
        val authResponse=AuthResponse("retorned",token,id,user)
        sharePreferencesPYAndroid(authResponse)

        buttonLoadImage.setOnClickListener {
            if (checkPermissionForGallery()) {
                openGallery()
            } else {
                requestPermissionForGallery()
            }
        }

        btSaveCreateAd.setOnClickListener {
            if (selectedImageUri != null) {
                RegisterAds()
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.ToastImageSelected),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        buttonCancel.setOnClickListener() {
            finish()
        }
    }

    fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

    fun saveImageToGallery(context: Context, bitmap: Bitmap, albumName: String): Uri? {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "qr_code.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$albumName")
            put(MediaStore.Images.Media.IS_PENDING, true)
        }

        val uri: Uri? = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let {
            context.contentResolver.openOutputStream(it).use { out ->
                if (out != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, false)
            context.contentResolver.update(uri, values, null, null)
        }

        return uri
    }

    private fun loadDataIntent() {
        ads.estatusAnuncio = intent.getSerializableExtra("state").toString()

        val priceString = intent.getStringExtra("price")
        try {
            val priceInt = priceString?.toInt() ?: 0
            ads.precioAnuncio = priceInt.toFloat()
        } catch (e: NumberFormatException) {
            ads.precioAnuncio = 0F
        }

        ads.qrAnuncio = "INHABILITATE"

        ads.nombreAnuncio = intent.getSerializableExtra("name").toString()
        user = intent.getSerializableExtra("user").toString()
        token = intent.getSerializableExtra("token").toString()

        val idString = intent.getStringExtra("idV")
        Log.d("loadata",idString.toString())
        try {
            val idInt = idString?.toInt() ?: 0
            ads.idVendedorAnuncio = idInt
            id=idInt
        } catch (e: NumberFormatException) {
            ads.idVendedorAnuncio = 0
        }

        loadTianguisId(ads)
        //


        val quantityString = intent.getStringExtra("quantity")
        try {
            val quantityInt = quantityString?.toInt() ?: 0
            ads.cantidadAnuncio = quantityInt
        } catch (e: NumberFormatException) {
            ads.cantidadAnuncio = 0
        }




        val categoryId = intent.getStringExtra("category")
        try {
            val categoryInt = categoryId?.toInt() ?: 0
            ads.idCategoriaAnuncio = categoryInt
        } catch (e: NumberFormatException) {
            ads.idCategoriaAnuncio = 0
        }
    }
<<<<<<< HEAD
    private fun sharePreferencesPYAndroid(loginResponse: AuthResponse) {
        val preferenceName = "my_app_information"
        val prefs: SharedPreferences = getSharedPreferences(preferenceName, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val loginResponseJson = gson.toJson(loginResponse)
        Log.d("JSONDATA", loginResponseJson.toString())
        editor.putString("datos_usuario", loginResponseJson)
        editor.apply()
    }
=======

>>>>>>> 74ca342 (Lista de productos reservados)
    private fun loadTianguisId(ads: AdsSeller) {
        Log.d("Tianguis","TG1")
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<UserSeller> = service.getSeller(token, ads.idVendedorAnuncio)
        Log.d("Tianguis","TG2")
        result.enqueue(object : Callback<UserSeller> {
            override fun onResponse(call: Call<UserSeller>, response: Response<UserSeller>) {
                if (response.isSuccessful) {

                    Usersellerresponse = response.body()!!
                    Log.d("Tianguis","${Usersellerresponse.toString()}")
                    ads.idTianguisAnuncio = Usersellerresponse.idTianguisVendedor
                } else {
                    when (response.code()) {
                        401 -> {
                            Toast.makeText(applicationContext, "Error de autorización", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(applicationContext, "Error del servidor", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(applicationContext, "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserSeller>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.ToastErrorResponse),
                    Toast.LENGTH_SHORT
                ).show()
                println(t.message)
            }

        })

    }

    private fun initComponents() {
        imageView = binding.ivNewImage
        buttonLoadImage = binding.btLoadImage
        buttonCancel = binding.btCancelCreateAdImage
        btSaveCreateAd = binding.btSaveCreateAd
    }

    private fun RegisterAds() {
<<<<<<< HEAD
        binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso
=======
>>>>>>> 74ca342 (Lista de productos reservados)
        var AdsAux: AdsSeller = ads
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)
        val imageFilePath = getPathFromUri(this, selectedImageUri!!)
        val imageFile = File(imageFilePath)

        val requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
        val adsJson = Gson().toJson(AdsAux)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", imageFile.name, requestFile)
            .addFormDataPart("adsData", adsJson)
            .build()

        val result: Call<ApiResponse> =
            service.addAdvertisement(token, requestBody)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    responseAuth = response.body()!!
                    Toast.makeText(applicationContext, "Busca tu QR en tu galeria", Toast.LENGTH_SHORT).show()

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(5000) // Espera 5 segundos (5000 milisegundos)
                        withContext(Dispatchers.Main) {
                            // Oculta la ProgressBar después del retraso
                            binding.progressBar.visibility = View.GONE
                            val qrCodeBitmap = generateQRCode(responseAuth.idTianguisAnuncio.toString())
                            val savedUri = saveImageToGallery(this@CreateAdImageActivity, qrCodeBitmap, "MyQRAlbum")
                            Log.d("Anuncio","${responseAuth.toString()}")

                            // Ahora inicia la actividad después del retraso
                            val intent = Intent(this@CreateAdImageActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    // El código restante para manejar errores de respuesta
                    // ...
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.e("RegisterADS", "Error en la solicitud: ${t.message}")
                Toast.makeText(applicationContext, "No se ha podido registrar", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissionForGallery(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissionForGallery() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            IMAGE_PICK_REQUEST
        )
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.data
            imageView.setImageURI(selectedImageUri)
        }
    }

    fun getPathFromUri(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(uri, proj, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            return columnIndex?.let { cursor!!.getString(it) }
        } finally {
            cursor?.close()
        }
    }
}