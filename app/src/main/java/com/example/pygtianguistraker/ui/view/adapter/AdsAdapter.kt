
import android.content.Context
<<<<<<< HEAD
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
=======
import android.graphics.BitmapFactory
>>>>>>> 74ca342 (Lista de productos reservados)
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
<<<<<<< HEAD
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.Adsproduct
import com.example.pygtianguistraker.data.model.ApiResponse
import com.example.pygtianguistraker.data.model.UpdateAdvertisementRequest
import com.example.pygtianguistraker.data.model.addPulletApartAdvertisement
import com.example.pygtianguistraker.data.network.AdvertisementsApiClient
import com.example.pygtianguistraker.ui.view.ShowActivityDetailsProducts
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture
=======
import com.example.pygtianguistraker.data.model.Adsproduct
>>>>>>> 74ca342 (Lista de productos reservados)


class AdsAdapter(
    context: Context,
    var productList: ArrayList<Adsproduct>,
<<<<<<< HEAD
    var userType: String,
    var token:String,
    var id:Int
=======
    var userType: String
>>>>>>> 74ca342 (Lista de productos reservados)
): RecyclerView.Adapter<AdsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val NameProducto = itemView.findViewById(R.id.productNameTextView) as TextView
        val imageProduct = itemView.findViewById(R.id.productImageView) as ImageView
        val priceProduct = itemView.findViewById(R.id.productPriceTextView) as TextView
        val locationProduct = itemView.findViewById(R.id.productLocationTextView) as TextView
        val categoryProduct = itemView.findViewById(R.id.productCategoryTextView) as TextView
        val buttonReserver = itemView.findViewById(R.id.reserveButton) as Button
        val buttonFav = itemView.findViewById(R.id.favoriteButton) as ImageButton
        val buttonDetails = itemView.findViewById(R.id.detailsButton) as Button
        val status=itemView.findViewById(R.id.productNameTextView2) as TextView

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_ads_seller, parent, false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

<<<<<<< HEAD
=======

>>>>>>> 74ca342 (Lista de productos reservados)
        //decodificador Base64
        val product = productList[position]
        val decodedImage = Base64.decode(product.fotoAnuncio, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)

<<<<<<< HEAD

=======
>>>>>>> 74ca342 (Lista de productos reservados)
        holder.NameProducto.text=product.nombreAnuncio
        holder.imageProduct.setImageBitmap(bitmap)
        holder.priceProduct.text=product.precioAnuncio.toString()
        holder.locationProduct.text=product.TianguisAnuncio
        holder.categoryProduct.text=product.CategoriaAnuncio
<<<<<<< HEAD
        holder.status.text=product.estatusAnuncio
=======
>>>>>>> 74ca342 (Lista de productos reservados)

        if (userType == "Vendedor") {
            holder.buttonFav.visibility = View.GONE
        } else {
            holder.buttonFav.visibility = View.VISIBLE
        }

        if(product.estatusAnuncio=="Vendido"){
            holder.status.setTextColor(Color.GREEN)
        }else if(product.estatusAnuncio=="Disponible"){
            holder.status.setTextColor(Color.BLACK)
        }else if(product.estatusAnuncio=="Apartado"){
            holder.status.setTextColor(Color.RED)
        }

        if (userType == "Vendedor") {
            holder.buttonDetails.text = "Eliminar"
            if(holder.status.text=="Vendido"){
                holder.status.setTextColor(Color.GREEN)
                holder.buttonReserver.text = "Marcar como \n Disponible"
            }else if(holder.status.text=="Apartado"){
                holder.status.setTextColor(Color.RED)
                holder.buttonReserver.text = "Marcar como \n" +
                        " Disponible"
            }else{
                holder.status.setTextColor(Color.BLACK)
                holder.buttonReserver.text = "Marcar como vendido"
            }

            holder.buttonReserver.setOnClickListener {
<<<<<<< HEAD
                if(holder.status.text=="Disponible"){
                    holder.buttonReserver.text = "Marcar como \n" +
                            " Disponible"
                    holder.status.setTextColor(Color.GREEN)
                    Log.e("estatusAnuncio", product.idAnuncio.toString())
                    updateAdsSell(product.idAnuncio)
                    holder.status.text="Vendido"

                }else if(holder.status.text=="Vendido"){
                    holder.buttonReserver.text = "Marcar como vendido"
                    holder.status.setTextColor(Color.BLACK)
                    updateAdsSellAvaible(product.idAnuncio)
                    holder.status.text="Disponible"

                }else if(holder.status.text=="Apartado"){
                    holder.buttonReserver.text = "Marcar como vendido"
                    holder.status.setTextColor(Color.GREEN)
                    Log.e("estatusAnuncio", product.idAnuncio.toString())
                    updateAdsSell(product.idAnuncio)
                    holder.status.text="Vendido"
                }
            }
            holder.buttonDetails.setOnClickListener {
                // Acciones para el botón "Eliminar"
                Toast.makeText(holder.itemView.context, "En desarrollo...\uD83D\uDE09", Toast.LENGTH_SHORT).show()
=======
                // Acciones para el botón "Editar"
                Log.d("prueba", "Editar ${product.nombreAnuncio}")
            }
            holder.buttonDetails.setOnClickListener {
                // Acciones para el botón "Eliminar"
                Log.d("prueba", "Eliminar ${product.nombreAnuncio}")
>>>>>>> 74ca342 (Lista de productos reservados)
            }
        }
        else if (userType == "Comprador") {
            holder.buttonReserver.text = "Apartar"
            holder.buttonDetails.text = "Detalles"

            if(holder.status.text=="Vendido"){
                holder.buttonReserver.text = "No se puede apartar"
                holder.buttonReserver.isEnabled=false
            }else if(holder.status.text=="Apartado"){
                holder.buttonReserver.text = "No se puede apartar"
                holder.buttonReserver.isEnabled=false
            }

            holder.buttonReserver.setOnClickListener {
<<<<<<< HEAD
                if(holder.status.text=="Disponible"){
                    holder.buttonReserver.text = "Apartado"
                    holder.status.setTextColor(Color.RED)
                    reserverads(product.idAnuncio)
                    holder.status.text="Apartado"
                }else if(holder.status.text=="Apartado"){
                    holder.buttonReserver.text = "No se puede apartar"
                    holder.buttonReserver.isEnabled=false
                }else if(holder.status.text=="Vendido"){
                    holder.buttonReserver.text = "No se puede apartar"
                    holder.buttonReserver.isEnabled=false
                }
            }
            holder.buttonDetails.setOnClickListener {
                // Start ShowActivityDetailsProducts and pass the product details
                val context = holder.itemView.context
                val intent = Intent(context, ShowActivityDetailsProducts::class.java).apply {
                    putExtra("id", product.idAnuncio)
                }
                context.startActivity(intent)
=======
                // Acciones para el botón "Reservar"
                Log.d("prueba", "Reservar ${product.nombreAnuncio}")
            }
            holder.buttonDetails.setOnClickListener {
                // Acciones para el botón "Detalles"
                Log.d("prueba", "Detalles ${product.nombreAnuncio}")
>>>>>>> 74ca342 (Lista de productos reservados)
            }
        }

        holder.buttonFav.setOnClickListener {
            // Acciones para el botón de favoritos
            Log.d("prueba", "Favoritos ${product.nombreAnuncio}")
        }

    }
<<<<<<< HEAD

    private fun reserverads(idAnuncio: Int) {
        val completableFuture = CompletableFuture<ApiResponse>()

        // Crear un objeto UpdateAdvertisementRequest con la idAnuncio
        val request = addPulletApartAdvertisement(idAnuncio,id)
        // Convertir el objeto a JSON utilizando Gson
        val requestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(request))
        Log.e("estatusAnuncio", requestBody.toString())
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)

        val result: Call<ApiResponse> = service.updateAdsSellerAvaible(token, requestBody)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseAuth = response.body()!!
                } else {
                    // El código restante para manejar errores de respuesta
                    // ...
                    completableFuture.completeExceptionally(Throwable("Error en la respuesta"))
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("avabile", "Error en la solicitud: ${t.message}")
            }
        })
    }

    private fun updateAdsSellAvaible(idVendedorAnuncio: Int): CompletableFuture<ApiResponse>{
        val completableFuture = CompletableFuture<ApiResponse>()

        // Crear un objeto UpdateAdvertisementRequest con la idAnuncio
        val request = UpdateAdvertisementRequest(idVendedorAnuncio)
        Log.e("estatusAnuncio", request.toString())
        // Convertir el objeto a JSON utilizando Gson
        val requestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(request))
        Log.e("estatusAnuncio", requestBody.toString())
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)

        val result: Call<ApiResponse> = service.updateAdsSellerAvaible(token, requestBody)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseAuth = response.body()!!
                } else {
                    // El código restante para manejar errores de respuesta
                    // ...
                    completableFuture.completeExceptionally(Throwable("Error en la respuesta"))
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("avabile", "Error en la solicitud: ${t.message}")
            }
        })
        return completableFuture
    }

    private fun updateAdsSell(idVendedorAnuncio: Int) {
        val completableFuture = CompletableFuture<ApiResponse>()

        // Crear un objeto UpdateAdvertisementRequest con la idAnuncio
        val request = UpdateAdvertisementRequest(idVendedorAnuncio)
        Log.e("estatusAnuncio", request.toString())
        // Convertir el objeto a JSON utilizando Gson
        val requestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(request))
        Log.e("estatusAnuncio", requestBody.toString())
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)

        val result: Call<ApiResponse> = service.updateAdsSeller(token, requestBody)
        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseAuth = response.body()!!

                } else {

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("SELL", "Error en la solicitud: ${t.message}")
            }
        })
    }


    fun updateData(newData: List<Adsproduct>) {
        Log.d("cleardata",newData.toString())
=======
    fun updateData(newData: List<Adsproduct>) {
>>>>>>> 74ca342 (Lista de productos reservados)
        productList.clear() // Limpia la lista actual
        productList.addAll(newData) // Agrega los nuevos datos
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}