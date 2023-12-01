package com.example.pygtianguistraker.ui.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.UserBuyer
import com.example.pygtianguistraker.data.network.UserBuyerApiClient
import com.example.pygtianguistraker.databinding.RegisterActivityBuyerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RegisterUserActivitybuyer  : AppCompatActivity(){
    private lateinit var binding: RegisterActivityBuyerBinding
    private lateinit var message :String
    private val calendar = Calendar.getInstance()
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var date:String
    private lateinit var address:String
    private var buyer = UserBuyer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBuyerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etAddress.isEnabled=false

        binding.btNextSeller.setOnClickListener{
            if(!validData()){
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            }else{
                if(validEmail()){
                    val date = binding.etBirthdate3.text.toString().trim()
                    val birthdate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
                    if(isAdult(birthdate)){
                        if(validPassword()){
                            registerBuyer()
                            var intent= Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this,"lo siento, debe ser mayor de edad para registrarte",Toast.LENGTH_SHORT).show()
                        val intent= Intent(this, LoginActivity::class.java)
                        startActivity(intent)

                        finish()
                    }

                }else{
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

                }
            }
        }
        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding.etBirthdate3.setOnClickListener(){
            showDatePickerDialog()
        }
    }

    private fun registerBuyer() {
        loadData()
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserBuyerApiClient::class.java)
        val result: Call<UserBuyer> = service.addBuyer(buyer)
        result.enqueue(object : Callback<UserBuyer> {
            override fun onResponse(call: Call<UserBuyer>, response: Response<UserBuyer>) {
                if (response.isSuccessful) {
                    Log.d("code", response.code().toString())
                    if (response.code() == 200) {
                        Toast.makeText(applicationContext, "Comprador registrado", Toast.LENGTH_SHORT).show()
                    } else if(response.code() == 409) {
                        // Código de respuesta 409: Conflicto
                        Toast.makeText(applicationContext, "Error: Conflicto, el comprador ya está registrado", Toast.LENGTH_SHORT).show()
                    }else{
                        // Otro código de respuesta diferente de 409
                        Toast.makeText(applicationContext, "Error en el registro", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    println(response)
                    println(buyer.toString())
                }
            }

            override fun onFailure(call: Call<UserBuyer>, t: Throwable) {
                Toast.makeText(applicationContext, "No se ha podido registrar", Toast.LENGTH_SHORT).show()
                println(buyer)
            }

        })
    }

    private fun loadData() {
        val date = binding.etBirthdate3.text.toString().trim()
        val birthdate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
        buyer.nombreComprador = binding.etName.text.toString().trim()
        buyer.correoUsuario = binding.etEmail.text.toString().trim()
        buyer.ubicacionComprador = binding.etAddress.text.toString().trim()
        buyer.fechaNacimientoComprador = binding.etBirthdate3.text.toString().trim()
        Log.d("RegistroActivity", binding.etBirthdate3.text.toString().trim())

        buyer.contraseniaUsuario = binding.etPassword.text.toString().trim()
    }
    private fun validPassword(): Boolean {
        var validation: Boolean
        validation = false
        message = "Las contraseñas no coinciden"
        var lengPassword= binding.etConfirmPassword.text.toString().trim()
        if(lengPassword.length>=8){
            if(binding.etPassword.text.toString().trim().equals(binding.etConfirmPassword.text.toString().trim())){
                validation = true
            }
        }else{
            message="La contraseña debe ser mayor de 8 caracteres para ser valida"
        }


        return validation
    }

    private fun isAdult(birthdate: Date?): Boolean {
        if (birthdate == null) {
            return false
        }
        val cal = Calendar.getInstance()
        val today = cal.time
        cal.add(Calendar.YEAR, -18)
        val eighteenYearsAgo = cal.time

        return birthdate.before(eighteenYearsAgo)
    }

    private fun validEmail(): Boolean {
        email = binding.etEmail.text.toString().trim()
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        message = "formato del correo no es valido"
        return emailRegex.matches(email)
    }

    private fun validData(): Boolean {
        name = binding.etName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        date = binding.etBirthdate3.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        address = binding.etAddress.text.toString().trim()

        var validation: Boolean
        validation = true

        if(name.isEmpty()){
            validation = false
            message = "El nombre no puede estar vacio"
        }
        if(email.isEmpty()){
            validation = false
            message = "El correo no puede estar vacio"
        }
        if(date.isEmpty()){
            validation = false
            message = "La fecha de nacimiento no puede estar vacia"
        }
        if(password.isEmpty()) {
            validation = false
            message = "La contraseña no puede estar vacia"
        }
        if(address.isEmpty()){
            validation = false
            message = "La direccion no puede estar vacio"
        }
        return validation
    }
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                updateDateInView(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun updateDateInView(calendar: Calendar) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.etBirthdate3.setText(dateFormat.format(calendar.time))
    }

}