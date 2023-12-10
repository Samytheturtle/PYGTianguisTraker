package com.example.pygtianguistraker.ui.view.fragment

import AdsAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.AuthResponse
import com.google.gson.Gson

class HometabfragmentAds : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdsAdapter
    private lateinit var actionButton: ImageButton
    //private var typeUser :Int =0
    var originalAdsList = ArrayList<AdsSeller>()
    private var filterListCategory =ArrayList<AdsSeller>()
    private var filterListTianguis =ArrayList<AdsSeller>()
    private var selectedCategory = "Todas las Categorias..."
    private var selectedTianguis = "Todos los Tianguis..."

    private lateinit var userType: String
    private lateinit var token: String
    private  var id:Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_published_ads, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewAdsHome)
        recyclerView.layoutManager = LinearLayoutManager(context)
        actionButton = view.findViewById(R.id.buttonAccionHome)

        val prefs = activity?.getSharedPreferences("my_app_information", Context.MODE_PRIVATE)
        val datosGuardados = prefs?.getString("datos_usuario", null)

        if (datosGuardados != null) {
            // Los datos se encontraron en las preferencias compartidas
            // Haz algo con los datos recuperados

            // Por ejemplo, si estás usando Gson para analizar JSON en una clase de datos
            val gson = Gson()
            val usuario = gson.fromJson(datosGuardados, AuthResponse::class.java)
            token = usuario.token
            id = usuario.id
            userType = usuario.user

            // Realiza las operaciones que necesites con estos datos
        } else {
            // No se encontraron datos en las preferencias compartidas

        }
        changerSpinners(view)
        changeButtonImage()
        loadProducts()

        val searchView = view.findViewById<SearchView>(R.id.searchViewAdsHome)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    // El texto de búsqueda está vacío o nulo, reinicia la lista original
                    loadProducts()
                } else {
                    // Realiza la búsqueda con el nuevo texto
                    performSearch(newText)
                }
                return true
            }
        })
        val tianguisSpinner = view?.findViewById<Spinner>(R.id.tianguisSpinner)
        tianguisSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                selectedTianguis = parentView.getItemAtPosition(position).toString()

                if (selectedTianguis == "Todos los Tianguis...") {
                    // Si se selecciona "Todos los Tianguis...", reinicia la lista
                    loadProducts()
                    if(selectedCategory != "Todas las Categorias..."){//categoria fija
                        val filteredList = originalAdsList.filter { it.category == selectedCategory } as ArrayList<AdsSeller>
                        filterListCategory=filteredList
                        adapter.updateData(filteredList)
                    }

                } else {
                    if(selectedCategory != "Todas las Categorias..."){//categoria fija
                        loadProducts()
                        val filteredList1 = originalAdsList.filter { it.location == selectedTianguis } as ArrayList<AdsSeller>
                        val filteredList = filteredList1.filter { it.category == selectedCategory } as ArrayList<AdsSeller>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
                    }else{
                        val filteredList = originalAdsList.filter { it.location == selectedTianguis } as ArrayList<AdsSeller>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
                    }

                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se hace nada aquí
            }
        }

        val categorySpinner = view?.findViewById<Spinner>(R.id.categorySpinner)
        categorySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                selectedCategory = parentView.getItemAtPosition(position).toString()

                if (selectedCategory == "Todas las Categorias...") {
                    loadProducts()
                    // Si se selecciona "Todas las Categorias...", reinicia la lista
                    if(selectedTianguis != "Todos los Tianguis..."){ //Tianguis fijo
                        val filteredList = originalAdsList.filter { it.location == selectedTianguis } as ArrayList<AdsSeller>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
                    }
                } else {
                    if(selectedTianguis != "Todos los Tianguis..."){ //Tianguis fijo
                        loadProducts()
                        val filteredList1 = originalAdsList.filter { it.category == selectedCategory } as ArrayList<AdsSeller>
                        val filteredList = filteredList1.filter { it.location == selectedTianguis } as ArrayList<AdsSeller>
                        filterListCategory=filteredList
                        adapter.updateData(filteredList)
                    }else{
                        val filteredList = originalAdsList.filter { it.category == selectedCategory } as ArrayList<AdsSeller>
                        filterListCategory=filteredList

                        adapter.updateData(filteredList)
                    }

                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se hace nada aquí
            }
        }

        actionButton = view.findViewById(R.id.buttonAccionHome)
        actionButton.setOnClickListener { onActionButtonClicked() }


        return view
    }

    private fun changerSpinners(view: View) {
        // Agregar elementos al spinner de categoría
        val categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
        val categoryItems = arrayOf("Todas las Categorias...","Ropa", "Electronica", "Hogar","Comida")
        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryItems)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter


        ChangerCategory()
        ChangerTianguis()
        // Agregar elementos al spinner de tianguis
        val tianguisSpinner = view.findViewById<Spinner>(R.id.tianguisSpinner)
        val tianguisItems = arrayOf("Todos los Tianguis...","Tianguis Urban", "Tianguis Central", "Tianguis Plaza Cristal")
        val tianguisAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tianguisItems)
        tianguisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tianguisSpinner.adapter = tianguisAdapter
    }

    private fun ChangerCategory() {
        //TODO("Not yet implemented")
    }

    private fun ChangerTianguis (){
       // TODO("Not yet implemented")
    }
    private fun loadProducts() {
        val Adssellproduct = ArrayList<AdsSeller>()
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Pantalones de Mezclilla", "$40","Tianguis Urban","Ropa"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Teléfono Samsung Galaxy", "$300","Tianguis Central","Electronica"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Mesa de Comedor", "$150","Tianguis Plaza Cristal","Hogar"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Manzanas Frescas", "$2","Tianguis Urban","Comida"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Blusa Estampada", "$20","Tianguis Plaza Cristal","Ropa"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Laptop HP", "$500","Tianguis Central","Electronica"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Sofá de Cuero", "$200","Tianguis Central","Hogar"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Sandwich de Pollo", "$5","Tianguis Urban","Comida"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Vestido de Noche", "$30","Tianguis Plaza Cristal","Ropa"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Tablet Android", "$120","Tianguis Central","Electronica"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Lámpara de Pie", "$50","Tianguis Plaza Cristal","Hogar"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Frutas Variadas", "$3","Tianguis Urban","Comida"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Zapatos Deportivos", "$25","Tianguis Plaza Cristal","Ropa"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Cámara Canon EOS", "$400","Tianguis Central","Electronica"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Muebles de Jardín", "$180","Tianguis Central","Hogar"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Pizza Margarita", "$7","Tianguis Urban","Comida"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Camiseta Casual", "$15","Tianguis Plaza Cristal","Ropa"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Smartwatch Fitbit", "$80","Tianguis Central","Electronica"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Sillas de Comedor", "$100","Tianguis Plaza Cristal","Hogar"))
        Adssellproduct.add(AdsSeller(R.drawable.without_image, "Tacos de Asada", "$6","Tianguis Urban","Comida"))

        originalAdsList=Adssellproduct

        adapterConexion(Adssellproduct)
    }
    private fun changeButtonImage() {
        val imageResource = when (userType) {
            "Comprador" -> R.drawable.ic_qr // Reemplaza 'ic_qr' con el nombre de tu recurso drawable para QR
            "Vendedor" -> R.drawable.ic_plus // Reemplaza 'ic_plus' con el nombre de tu recurso drawable para el símbolo de más
            else -> R.drawable.ic_default // Opcional, en caso de un valor inesperado
        }
        actionButton.setImageResource(imageResource)
    }
    private fun adapterConexion(Adssellproduct: ArrayList<AdsSeller>) {
        adapter = AdsAdapter(requireContext(),Adssellproduct)
        recyclerView.adapter = adapter
    }
    private fun onActionButtonClicked() {
        when (userType) {
            "Vendedor"  -> {
                Log.d("prueba","1")
                // Acción para el typeUser 1
                // Por ejemplo, abrir un fragmento de escaneo de QR
            }
            "Comprador" -> {
                Log.d("prueba","0")
                // Acción para el typeUser 0
                // Por ejemplo, abrir un diálogo para agregar algo
            }
            else -> {
                Log.d("prueba","2")
                // Acción por defecto o para otros valores de typeUser
            }
        }
    }
    private fun performSearch(query: String) {
        val filteredAds = ArrayList<AdsSeller>()

        // Recorre todos los elementos originales y filtra los que coincidan con la consulta
        for (ad in originalAdsList) {
            if (ad.name.contains(query, true) || ad.category.contains(query, true) || ad.location.contains(query, true)) {
                filteredAds.add(ad)
            }
        }

        // Actualiza el adaptador con los resultados filtrados
        adapter.updateData(filteredAds)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}


