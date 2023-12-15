package com.example.pygtianguistraker.ui.view.fragment

import AdsAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AdsSeller
import com.example.pygtianguistraker.data.model.Adsproduct
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.Category
import com.example.pygtianguistraker.data.model.Tianguis
<<<<<<< HEAD
=======
import com.example.pygtianguistraker.data.network.AdvertisementsApiClient
>>>>>>> 74ca342 (Lista de productos reservados)
import com.example.pygtianguistraker.data.network.CategoryApiClient
import com.example.pygtianguistraker.data.network.UserSellerApiClient
import com.example.pygtianguistraker.ui.view.CreateAdActivity
import com.example.pygtianguistraker.ui.view.HomeActivity
import com.example.pygtianguistraker.ui.view.ShowActivityDetailsProducts
import com.example.pygtianguistraker.ui.viewModel.AdsViewModel
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
<<<<<<< HEAD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
=======
>>>>>>> 74ca342 (Lista de productos reservados)
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HometabfragmentAds : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdsAdapter
    private lateinit var actionButton: ImageButton

<<<<<<< HEAD
=======
    private var categoryNames = ArrayList<String>()
    private var selectedCategoryId: Int = -1
    private var idListCategory: ArrayList<Int> = ArrayList()

>>>>>>> 74ca342 (Lista de productos reservados)
    private lateinit var tianguisList: List<Tianguis>
    private lateinit var categoryList: List<Category>
    //private var typeUser :Int =0

    private lateinit var originalAdsList: List<AdsSeller>
    private lateinit var originalProductList: ArrayList<Adsproduct>
    //var originalAdsList = ArrayList<AdsSeller>()
    private var filterListCategory =ArrayList<Adsproduct>()
    private var filterListTianguis =ArrayList<Adsproduct>()
<<<<<<< HEAD
    private var selectedCategory = "Todas las categorías"
    private var selectedTianguis = "Todos los tianguis"

    private lateinit var adsViewModel: AdsViewModel
=======
    private var selectedCategory = "Todas las categorias..."
    private var selectedTianguis = "Todos los tianguis..."
>>>>>>> 74ca342 (Lista de productos reservados)

    private lateinit var userType: String
    private lateinit var token: String
    private  var id:Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.consult_activity_published_ads, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewAdsHome)
        recyclerView.layoutManager = LinearLayoutManager(context)
        actionButton = view.findViewById(R.id.buttonAccionHome)

        adsViewModel = ViewModelProvider(requireActivity()).get(AdsViewModel::class.java)

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

        }
<<<<<<< HEAD

        CoroutineScope(Dispatchers.IO).launch {
            getTianguis(view)
            withContext(Dispatchers.Main) {

            }
        }
=======
        loadProducts()
        getTianguis(view)
>>>>>>> 74ca342 (Lista de productos reservados)


        val searchView = view.findViewById<SearchView>(R.id.searchViewAdsHome)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recoverProducts()
                if (newText.isNullOrEmpty()) {
                    // El texto de búsqueda está vacío o nulo, reinicia la lista original
<<<<<<< HEAD
                    recoverProducts()
=======
                    //loadProducts()
                    //LoadDummysProducts()

>>>>>>> 74ca342 (Lista de productos reservados)
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
<<<<<<< HEAD
                recoverProducts()
                if (selectedTianguis == "Todos los tianguis") {
                    if (selectedCategory != "Todas las categorías") {
                        // Si se selecciona "Todos los tianguis" y la categoría no es "Todas las categorías",
                        // realiza la filtración solo por categoría
                        val filteredList = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListCategory = filteredList
                        Log.d("Filter", filterListCategory.toString())
                        adapter.updateData(filterListCategory)
                    } else {
                        // Si se selecciona "Todos los tianguis" y "Todas las categorías", muestra todos los productos originales
                        Log.d("1Filter","ALL C ALL T")
                        //Log.d("1Filter",originalProductList.toString())
                        recoverProducts()
=======

                if (selectedTianguis == "Todos los tianguis...") {
                    // Si se selecciona "Todos los Tianguis...", reinicia la lista "
                    //loadProducts()
                    //LoadDummysProducts()
                    if(selectedCategory != "Todas las categorias..."){//categoria fija
                        val filteredList = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListCategory=filteredList
                        adapter.updateData(filteredList)
>>>>>>> 74ca342 (Lista de productos reservados)
                    }
                } else {
<<<<<<< HEAD
                    // Si se selecciona un tianguis específico
                    if (selectedCategory != "Todas las categorías") {
                        // Si también se ha seleccionado una categoría específica, filtra por ambas
                        val filteredList1 = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        val filteredList = filteredList1.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListTianguis = filteredList
                        Log.d("Filter", filterListTianguis.toString())
                        adapter.updateData(filterListTianguis)
                    } else {
                        // Si solo se ha seleccionado un tianguis específico, filtra solo por tianguis
                        val filteredList = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListTianguis = filteredList
                        Log.d("Filter", filterListTianguis.toString())
                        adapter.updateData(filterListTianguis)
=======
                    if(selectedCategory != "Todas las categorias..."){//categoria fija
                        //loadProducts()
                        //LoadDummysProducts()
                        val filteredList1 = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        val filteredList = filteredList1.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
                    }else{
                        val filteredList = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
>>>>>>> 74ca342 (Lista de productos reservados)
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
<<<<<<< HEAD
                selectedCategory = parentView.getItemAtPosition(position).toString()
                recoverProducts()
                if (selectedCategory == "Todas las categorías") {
                    if (selectedTianguis != "Todos los tianguis") {
                        // Si se selecciona "Todas las categorías" y el tianguis no es "Todos los tianguis",
                        // realiza la filtración solo por tianguis
                        val filteredList = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListTianguis = filteredList
                        Log.d("Filter", filterListTianguis.toString())
                        adapter.updateData(filterListTianguis)
                    } else {
                        // Si se selecciona "Todas las categorías" y "Todos los tianguis", muestra todos los productos originales
                        //Log.d("Filter","ALL C ALL T")
                        recoverProducts()
                    }
                } else {
                    // Si se selecciona una categoría específica
                    if (selectedTianguis != "Todos los tianguis") {
                        // Si también se ha seleccionado un tianguis específico, filtra por ambas
                        val filteredList1 = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        val filteredList = filteredList1.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListCategory = filteredList
                        Log.d("Filter", filterListCategory.toString())
                        adapter.updateData(filterListCategory)
                    } else {
                        // Si solo se ha seleccionado una categoría específica, filtra solo por categoría
                        val filteredList = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListCategory = filteredList
                        Log.d("Filter", filterListCategory.toString())
                        adapter.updateData(filterListCategory)
=======

                if (selectedCategory == "Todas las categorias...") {
                    //loadProducts()
                    //LoadDummysProducts()
                    // Si se selecciona "Todas las Categorias...", reinicia la lista
                    if(selectedTianguis != "Todos los Tianguis..."){ //Tianguis fijo
                        val filteredList = originalProductList.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListTianguis=filteredList
                        adapter.updateData(filteredList)
                    }
                } else {
                    if(selectedTianguis != "Todos los Tianguis..."){ //Tianguis fijo
                        //loadProducts()
                        //LoadDummysProducts()
                        val filteredList1 = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        val filteredList = filteredList1.filter { it.TianguisAnuncio == selectedTianguis } as ArrayList<Adsproduct>
                        filterListCategory=filteredList
                        adapter.updateData(filteredList)
                    }else{
                        val filteredList = originalProductList.filter { it.CategoriaAnuncio == selectedCategory } as ArrayList<Adsproduct>
                        filterListCategory=filteredList

                        adapter.updateData(filteredList)
>>>>>>> 74ca342 (Lista de productos reservados)
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
    private fun loadCategories(view: View) {
        Log.d("loadCategories","1")
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(CategoryApiClient::class.java)
        val result: Call<List<Category>> = service.getCategorys()
        Log.d("loadCategories","2")
        result.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    Log.d("loadCategories","3")
                    categoryList= response.body() ?: emptyList()
                    Log.d("loadCategories",categoryList.toString())
                    changerSpinners(view)

                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                activity?.let {
                    Log.d("loadCategories",t.message.toString())
                    Toast.makeText(it, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("CreateAdActivity", "Error en la llamada: ${t.message}")
                }
            }
        })
    }
    private fun getTianguis(view: View) {
        Log.d("getTianguis","1")
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<List<Tianguis>> = service.getTianguis()
        Log.d("getTianguis","2")
        result.enqueue(object : Callback<List<Tianguis>>{

            override fun onResponse(
                call: Call<List<Tianguis>>,
                response: Response<List<Tianguis>>
            ) {
                if(response.isSuccessful) {
                    Log.d("getTianguis","3")
                    tianguisList = response.body()!!
                    Log.d("getTianguis",tianguisList.toString())
                    activity?.let {
<<<<<<< HEAD
                        Toast.makeText(it, "Productos cargados :D", Toast.LENGTH_SHORT).show()
=======
                        Toast.makeText(it, "Bien hecho", Toast.LENGTH_SHORT).show()
>>>>>>> 74ca342 (Lista de productos reservados)
                    }
                    loadCategories(view)

                }
            }

            override fun onFailure(call: Call<List<Tianguis>>, t: Throwable) {
                activity?.let {
                    Log.d("getTianguis",t.message.toString())
                    Toast.makeText(it, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT).show()
                    println(t.message)
                }

            }

        })
    }
    private fun LoadDummysProducts(){
        val Adssellproduct = ArrayList<AdsSeller>()
        Adssellproduct.add(AdsSeller(idAnuncio = 0, estatusAnuncio = "Disponible", fotoAnuncio = "", precioAnuncio = 40.0f, qrAnuncio = "Código QR", nombreAnuncio = "Pantalones de Mezclilla", idTianguisAnuncio = 1, cantidadAnuncio = 0, idVendedorAnuncio = 0, idCategoriaAnuncio = 0))


        originalAdsList=Adssellproduct

        adapterConexion(Adssellproduct)
    }
<<<<<<< HEAD

=======
    private fun loadProducts() {
        Log.d("loadProducts","1")
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(AdvertisementsApiClient::class.java)
        val result: Call<List<AdsSeller>> = service.getAllAds()
        Log.d("loadProducts","2")
        //Log.d("loadProducts",result.toString())
        result.enqueue(object : Callback<List<AdsSeller>> {
            override fun onResponse(call: Call<List<AdsSeller>>, response: Response<List<AdsSeller>>) {

                Log.d("loadProducts", "Response code: EEEEEEEEEEEEEE")
                if (response.isSuccessful) {
                    Log.d("loadProducts","3")
                    originalAdsList= response.body() ?: emptyList()
                    Log.d("loadProducts",originalAdsList.toString())
                    adapterConexion(originalAdsList)
                }else {
                    when (response.code()) {
                        401 -> {
                            activity?.let{
                                Toast.makeText(it, "Error de autorización", Toast.LENGTH_SHORT).show()
                            }

                        }
                        500 -> {
                            activity?.let{
                                Toast.makeText(it, "Error del servidor", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else -> {
                            activity?.let{
                                Log.e("loadProducts", "Error desconocido")
                                Toast.makeText(it, "Error desconocido", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<AdsSeller>>, t: Throwable) {
                Log.e("loadProducts", "Failure")
                activity?.let {
                    Toast.makeText(it, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("loadProducts", "Error en la llamada: ${t.message}")
                }
            }

        })


    }
>>>>>>> 74ca342 (Lista de productos reservados)
    private fun getCategoryNameById(categoryId: Int): String {
        Log.d("getCategoryNameById","1")
        val category = categoryList.find { it.idCategoria == categoryId }
        return category?.nombreCategoria ?: ""
    }
    private fun getTianguisNameById(tianguisId: Int): String {
        Log.d("getTianguisNameById","1")
        val tianguis = tianguisList.find { it.idTianguis == tianguisId }
        return tianguis?.nombreTianguis ?: ""
    }
    private fun adapterConexion(AdssellerList: List<AdsSeller>) {
<<<<<<< HEAD
        Log.d("adapterConexion","1")
=======
>>>>>>> 74ca342 (Lista de productos reservados)
        val adsProductList = ArrayList<Adsproduct>()
        Log.d("adapterConexion","1")
        for (adsSeller in AdssellerList) {
            Log.d("adapterConexion","buble")
            val category = getCategoryNameById(adsSeller.idCategoriaAnuncio)
            val tianguis = getTianguisNameById(adsSeller.idTianguisAnuncio)

            val adsProduct = Adsproduct(
<<<<<<< HEAD
                adsSeller.idAnuncio,
=======
>>>>>>> 74ca342 (Lista de productos reservados)
                adsSeller.estatusAnuncio,
                adsSeller.precioAnuncio,
                adsSeller.fotoAnuncio,
                adsSeller.qrAnuncio,
                adsSeller.nombreAnuncio,
                tianguis,
                adsSeller.cantidadAnuncio,
                adsSeller.idVendedorAnuncio,
                category
            )

            adsProductList.add(adsProduct)
<<<<<<< HEAD
            Log.d("LIST",adsProduct.nombreAnuncio)
            Log.d("LIST",adsProduct.TianguisAnuncio)
            Log.d("LIST",adsProduct.CategoriaAnuncio)
        }

        originalProductList=adsProductList

        Log.d("LIST",originalProductList[0].nombreAnuncio)

        adapter = AdsAdapter(requireContext(),adsProductList,userType,token,id)
        recyclerView.adapter = adapter
    }
    private fun changerSpinners(view: View) {
        // Agrega "Todas las categorías" y "Todos los tianguis" a las listas de nombres
        val categoryNames = mutableListOf("Todas las categorías")
        categoryNames.addAll(categoryList.map { it.nombreCategoria })

        val tianguisNames = mutableListOf("Todos los tianguis")
        tianguisNames.addAll(tianguisList.map { it.nombreTianguis })

        activity?.let {
            val categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
            val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = categoryAdapter

            // Selecciona "Todas las categorías" inicialmente
            categorySpinner.setSelection(0)
        }

        activity?.let {
            val tianguisSpinner = view.findViewById<Spinner>(R.id.tianguisSpinner)
            val tianguisAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tianguisNames)
            tianguisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tianguisSpinner.adapter = tianguisAdapter

            // Selecciona "Todos los tianguis" inicialmente
            tianguisSpinner.setSelection(0)
        }

        changeButtonImage()
    }


=======
        }

        originalProductList=adsProductList
        adapter = AdsAdapter(requireContext(),adsProductList,userType)
        recyclerView.adapter = adapter
    }
    private fun changerSpinners(view: View) {

        activity?.let{
            val categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
            val categoryNames = categoryList.map { it.nombreCategoria }
            val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = categoryAdapter
        }

        activity?.let{
            val tianguisSpinner = view.findViewById<Spinner>(R.id.tianguisSpinner)
            val tianguisNames = tianguisList.map { it.nombreTianguis }
            val tianguisAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tianguisNames)
            tianguisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tianguisSpinner.adapter = tianguisAdapter
        }
        changeButtonImage()

    }

>>>>>>> 74ca342 (Lista de productos reservados)
    private fun changeButtonImage() {
        val imageResource = when (userType) {
            "Comprador" -> R.drawable.ic_qr // Reemplaza 'ic_qr' con el nombre de tu recurso drawable para QR
            "Vendedor" -> R.drawable.ic_plus // Reemplaza 'ic_plus' con el nombre de tu recurso drawable para el símbolo de más
            else -> R.drawable.ic_default // Opcional, en caso de un valor inesperado
        }
        actionButton.setImageResource(imageResource)
<<<<<<< HEAD

        recoverProducts()
        //LoadDummysProducts()
    }

    private fun recoverProducts() {
        val originalAdsList = adsViewModel.originalAdsList
        Log.d("recover2",originalAdsList.toString())

        if (originalAdsList != null) {
            // Utiliza originalAdsList en tu fragmento
            adapterConexion(originalAdsList)
        }else{
            activity?.let {
                Toast.makeText(it, "Lista vacia", Toast.LENGTH_SHORT).show()
            }
        }

    }


=======
        //loadProducts()
        LoadDummysProducts()
    }

>>>>>>> 74ca342 (Lista de productos reservados)
    private fun onActionButtonClicked() {
        when (userType) {
            "Comprador" -> {
                // Acción para el tipo de usuario "Comprador"
                initScanner()
            }
            "Vendedor" -> {
                // Acción para el tipo de usuario "Vendedor"
                val intent = Intent(requireContext(), CreateAdActivity::class.java)
                startActivity(intent)
                val homeActivity = activity
                if (homeActivity is HomeActivity) {
                    homeActivity.finish()
                }


            }
            else -> {
                Log.d("prueba", "2")
                // Acción por defecto o para otros valores de tipo de usuario
            }
        }
    }
    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permiso concedido, proceder con la funcionalidad
                } else {
                    // Permiso denegado, manejar la situación
                }
                return
            }
            // Verificar otros permisos de la aplicación
        }
    }


    private fun initScanner() {
        activity?.let {
            val integrator = IntentIntegrator(it)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt(getString(R.string.ScanTestInstrucion))
            integrator.setTorchEnabled(false)
            integrator.setBeepEnabled(true)
            integrator.initiateScan()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.d("Revisor", "PRE")
        if (result != null) {
            Log.d("Revisor", "YES")
            if (result.contents == null) {
                activity?.let {
                    Toast.makeText(it, "Cancelado", Toast.LENGTH_LONG).show()
                    Log.d("Revisor", "Cancelado")
                }
            } else {
                Log.d("Revisor", "NOOOOOOO")
                val scannedValue = result.contents
                if (scannedValue.isDigitsOnly()) { // Verifica si el valor escaneado es un número
                    val context = activity ?: return
                    val intent = Intent(context, ShowActivityDetailsProducts::class.java).apply {
                        putExtra("id", scannedValue.toInt()) // Convierte el valor escaneado a un entero
                    }
                    context.startActivity(intent)
                } else {
                    activity?.let {
                        Toast.makeText(it, "El valor escaneado es: $scannedValue", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



    private fun performSearch(query: String) {
        val filteredAds = ArrayList<Adsproduct>()

        // Recorre todos los elementos originales y filtra los que coincidan con la consulta
        for (ad in originalProductList) {
<<<<<<< HEAD
            if (ad.nombreAnuncio.contains(query, true)) {
=======
            if (ad.nombreAnuncio.contains(query, true) || ad.CategoriaAnuncio.contains(query, true) || ad.TianguisAnuncio.contains(query, true)) {
>>>>>>> 74ca342 (Lista de productos reservados)
                filteredAds.add(ad)
            }
        }

        // Actualiza el adaptador con los resultados filtrados
        adapter.updateData(filteredAds)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        //recoverProducts()
        // Coloca aquí el código para recargar o actualizar los datos del Fragment
        // Por ejemplo, puedes volver a cargar la lista de productos, si es necesario.
    }
}

