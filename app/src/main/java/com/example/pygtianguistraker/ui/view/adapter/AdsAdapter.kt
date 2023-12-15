
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.Adsproduct


class AdsAdapter(
    context: Context,
    var productList: ArrayList<Adsproduct>,
    var userType: String
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

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_ads_seller, parent, false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //decodificador Base64
        val product = productList[position]
        val decodedImage = Base64.decode(product.fotoAnuncio, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)

        holder.NameProducto.text=product.nombreAnuncio
        holder.imageProduct.setImageBitmap(bitmap)
        holder.priceProduct.text=product.precioAnuncio.toString()
        holder.locationProduct.text=product.TianguisAnuncio
        holder.categoryProduct.text=product.CategoriaAnuncio

        if (userType == "Vendedor") {
            holder.buttonFav.visibility = View.GONE
        } else {
            holder.buttonFav.visibility = View.VISIBLE
        }

        if (userType == "Vendedor") {
            holder.buttonReserver.text = "Editar"
            holder.buttonDetails.text = "Eliminar"

            holder.buttonReserver.setOnClickListener {
                // Acciones para el botón "Editar"
                Log.d("prueba", "Editar ${product.nombreAnuncio}")
            }
            holder.buttonDetails.setOnClickListener {
                // Acciones para el botón "Eliminar"
                Log.d("prueba", "Eliminar ${product.nombreAnuncio}")
            }
        } else if (userType == "Comprador") {
            holder.buttonReserver.text = "Reservar"
            holder.buttonDetails.text = "Detalles"

            holder.buttonReserver.setOnClickListener {
                // Acciones para el botón "Reservar"
                Log.d("prueba", "Reservar ${product.nombreAnuncio}")
            }
            holder.buttonDetails.setOnClickListener {
                // Acciones para el botón "Detalles"
                Log.d("prueba", "Detalles ${product.nombreAnuncio}")
            }
        }

        holder.buttonFav.setOnClickListener {
            // Acciones para el botón de favoritos
            Log.d("prueba", "Favoritos ${product.nombreAnuncio}")
        }

    }
    fun updateData(newData: List<Adsproduct>) {
        productList.clear() // Limpia la lista actual
        productList.addAll(newData) // Agrega los nuevos datos
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}
