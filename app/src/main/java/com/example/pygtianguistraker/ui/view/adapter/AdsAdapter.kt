
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.AdsSeller

class AdsAdapter(context: Context, private val data: List<AdsSeller>) :
    ArrayAdapter<AdsSeller>(context, R.layout.fragment_ads_seller, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val holder: ViewHolder

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.fragment_ads_seller, parent, false)
            holder = ViewHolder()
            // Asignar vistas al ViewHolder
            holder.imageViewProduct = view.findViewById(R.id.productImageView)
            holder.textViewProductName = view.findViewById(R.id.productNameTextView)
            holder.textViewPrice = view.findViewById(R.id.productPriceTextView)
            holder.textViewLocation = view.findViewById(R.id.productLocationTextView)
            holder.buttonReserve = view.findViewById(R.id.reserveButton)
            holder.buttonDetails = view.findViewById(R.id.detailsButton)
            holder.buttonfavority= view.findViewById(R.id.favoriteButton)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val currentItem = data[position]

        // Asignar valores a las vistas
        holder.imageViewProduct?.setImageResource(currentItem.imageResource)
        holder.textViewProductName?.text = currentItem.name
        holder.textViewPrice?.text = currentItem.price
        holder.textViewLocation?.text = currentItem.location
        return view!!
    }

    // Clase ViewHolder para mantener las referencias a las vistas
    private class ViewHolder {
        var imageViewProduct: ImageView? = null
        var textViewProductName: TextView? = null
        var textViewPrice: TextView? = null
        var textViewLocation: TextView? = null
        var buttonReserve:Button?=null
        var buttonDetails:Button?=null
        var buttonfavority:ImageButton?=null
    }
}

