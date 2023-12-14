package com.example.pygtianguistraker.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.Tianguis
import com.example.pygtianguistraker.data.model.UserSeller
import com.example.pygtianguistraker.data.network.UserSellerApiClient
import com.example.pygtianguistraker.databinding.RegisterActivityTianguisBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserActivityTianguis : AppCompatActivity() {

    private lateinit var binding:RegisterActivityTianguisBinding
    private lateinit var tianguisList: List<Tianguis>
    private var selectedDays: MutableList<String> = mutableListOf()
    private var seller = UserSeller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityTianguisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seller.nombreVendedor = intent.getSerializableExtra("nombreVendedor").toString()
        seller.correoUsuario = intent.getSerializableExtra("correo").toString()
        seller.contraseniaUsuario = intent.getSerializableExtra("password").toString()
        seller.fechaNacimientoVendedor = intent.getSerializableExtra("date").toString()
        seller.calificacionVendedor ="0";
        getTianguis()
        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btRegisterSeller.setOnClickListener{
            if(getSelectedCheckBoxes()){
                registerSeller()
                var intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.spiTianguis.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                loadHours(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun getSelectedCheckBoxes(): Boolean{
        var validation: Boolean
        validation = false
        selectedDays.clear()
        if (binding.cbMonday.isChecked) {
            selectedDays.add("Lunes")
        }else{
            seller.horarioLunesVendedor =""
        }
        if (binding.cbTuesday.isChecked) {
            selectedDays.add("Martes")
        }else{
            seller.horarioMartesVendedor = ""
        }
        if (binding.cbWednesday.isChecked) {
            selectedDays.add("Miércoles")
        }else{
            seller.horarioMiercolesVendedor = ""
        }
        if (binding.cbThursday.isChecked) {
            selectedDays.add("Jueves")
        }else{
            seller.horarioJuevesVendedor = ""
        }
        if (binding.cbFriday.isChecked) {
            selectedDays.add("Viernes")
        }else{
            seller.horarioViernesVendedor = ""
        }
        if (binding.cbSaturday.isChecked) {
            selectedDays.add("Sábado")
        }else{
            seller.horarioSabadoVeneddor = ""
        }
        if (binding.cbSunday.isChecked) {
            selectedDays.add("Domingo")
        }else{
            seller.horarioDomingoVendedor = ""
        }
        if (selectedDays.isNotEmpty()) {
            validation = true

        } else {
            // Si no hay días seleccionados, muestra un mensaje de error
            Toast.makeText(applicationContext, "Por favor, selecciona al menos un día.", Toast.LENGTH_SHORT).show()
        }
        return validation
    }

    private fun registerSeller() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<UserSeller> = service.addSeller(seller)
        result.enqueue(object : Callback<UserSeller>{
            override fun onResponse(call: Call<UserSeller>, response: Response<UserSeller>) {
                if (response.isSuccessful) {
                    if (response.code() == 409) {
                        // Código de respuesta 409: Conflicto
                        Toast.makeText(applicationContext, "Error: Conflicto, el vendedor ya está registrado", Toast.LENGTH_SHORT).show()
                    } else if(response.code()==200) {
                        Toast.makeText(applicationContext, "Vendedor registrado", Toast.LENGTH_SHORT).show()
                    }else{
                        // Otro código de respuesta diferente de 409
                        Toast.makeText(applicationContext, "Error en el registro", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    println(response)
                    println(seller.toString())
                }
            }

            override fun onFailure(call: Call<UserSeller>, t: Throwable) {
                Toast.makeText(applicationContext, "No se ha podido registrar", Toast.LENGTH_SHORT).show()
                println(t)
            }

        })
    }

    @SuppressLint("ResourceType")
    private fun loadData() {
        val namesTianguis = ArrayList<String>()
        for (tianguis in tianguisList){
            namesTianguis.add(tianguis.nombreTianguis)
        }
        val adapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            namesTianguis
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spiTianguis.adapter= adapter
    }

    private fun loadHours(selectedItem: String) {
        for (tianguis in tianguisList){
            if(tianguis.nombreTianguis==selectedItem){
                seller.horarioLunesVendedor = tianguis.horarioLunesTianguis
                seller.horarioMartesVendedor = tianguis.horarioMartesTianguis
                seller.horarioMiercolesVendedor = tianguis.horarioMiercolesTianguis
                seller.horarioJuevesVendedor = tianguis.horarioJuevesTianguis
                seller.horarioViernesVendedor = tianguis.horarioViernesTianguis
                seller.horarioSabadoVeneddor = tianguis.horarioSabadoTianguis
                seller.horarioDomingoVendedor = tianguis.horarioDomingoTianguis
                seller.idTianguisVendedor = tianguis.idTianguis
                binding.tvDirectionTianguis.setText(tianguis.direccionTianguis)
                binding.tvMonday.setText(tianguis.horarioLunesTianguis)
                binding.tvThursday.setText(tianguis.horarioMartesTianguis)
                binding.tvWednesday.setText(tianguis.horarioMiercolesTianguis)
                binding.tvTuesday.setText(tianguis.horarioJuevesTianguis)
                binding.tvFriday.setText(tianguis.horarioViernesTianguis)
                binding.tvSaturday.setText(tianguis.horarioSabadoTianguis)
                binding.tvSunday.setText(tianguis.horarioDomingoTianguis)
                break
            }
        }
    }

    private fun getTianguis() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<List<Tianguis>> = service.getTianguis()
        result.enqueue(object : Callback<List<Tianguis>>{
            override fun onResponse(
                call: Call<List<Tianguis>>,
                response: Response<List<Tianguis>>
            ) {
                if(response.isSuccessful){
                    tianguisList = response.body()!!
                    Toast.makeText(applicationContext, "Bien hecho", Toast.LENGTH_SHORT).show()
                    loadData()
                }
            }

            override fun onFailure(call: Call<List<Tianguis>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT).show()
                println(t.message)
            }

        })
    }
}