package com.example.pygtianguistraker.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.FavProduct
import com.example.pygtianguistraker.ui.view.AdapterFavProduct


class ConsultActivityFavProducts : Fragment(){
    private lateinit var listView: ListView
    private lateinit var adapter: AdapterFavProduct
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_fav_products, container, false)
        listView = view.findViewById(R.id.listViewFavoriteProducts)


        val favProducts = ArrayList<FavProduct>()
        favProducts.add(FavProduct(R.drawable.without_image, "Camisa Naruto", "$50", "Tianguis A"))
        favProducts.add(FavProduct(R.drawable.without_image, "Zapatos Deportivos", "$80", "Tianguis B"))
        favProducts.add(FavProduct(R.drawable.without_image, "Bolsa Elegante", "$120", "Tianguis C"))
        favProducts.add(FavProduct(R.drawable.without_image, "Gafas de Sol", "$30", "Tianguis D"))
        favProducts.add(FavProduct(R.drawable.without_image, "Reloj de Pulsera", "$100", "Tianguis E"))
        favProducts.add(FavProduct(R.drawable.without_image, "Collar de Perlas", "$60", "Tianguis F"))
        favProducts.add(FavProduct(R.drawable.without_image, "Gorra de Moda", "$25", "Tianguis G"))
        favProducts.add(FavProduct(R.drawable.without_image, "Maletín Ejecutivo", "$150", "Tianguis H"))
        favProducts.add(FavProduct(R.drawable.without_image, "Juegos de Mesa", "$40", "Tianguis I"))
        favProducts.add(FavProduct(R.drawable.without_image, "Teléfono Móvil", "$300", "Tianguis J"))
        favProducts.add(FavProduct(R.drawable.without_image, "Teclado Inalámbrico", "$50", "Tianguis K"))
        favProducts.add(FavProduct(R.drawable.without_image, "Cámara Digital", "$200", "Tianguis L"))
        favProducts.add(FavProduct(R.drawable.without_image, "Laptop Ultraligera", "$600", "Tianguis M"))
        favProducts.add(FavProduct(R.drawable.without_image, "Botas de Cuero", "$90", "Tianguis N"))
        favProducts.add(FavProduct(R.drawable.without_image, "Bicicleta de Montaña", "$250", "Tianguis O"))
        favProducts.add(FavProduct(R.drawable.without_image, "Libros Clásicos", "$20", "Tianguis P"))
        favProducts.add(FavProduct(R.drawable.without_image, "Pelotas de Golf", "$15", "Tianguis Q"))
        favProducts.add(FavProduct(R.drawable.without_image, "Pinturas Artísticas", "$70", "Tianguis R"))
        favProducts.add(FavProduct(R.drawable.without_image, "Joyas Vintage", "$180", "Tianguis S"))
        favProducts.add(FavProduct(R.drawable.without_image, "Lámpara Retro", "$35", "Tianguis T"))

        adapter = AdapterFavProduct(requireContext(), favProducts)
        listView.adapter = adapter

        return view
    }
}