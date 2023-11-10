package com.example.pygtianguistraker.ui.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.data.model.UserSeller
import com.example.pygtianguistraker.databinding.RegisterActivitySellerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterUserActivitySeller : AppCompatActivity(){
    private lateinit var binding:RegisterActivitySellerBinding
    private lateinit var message :String
    private val calendar = Calendar.getInstance()
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivitySellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btNextSeller.setOnClickListener{
            if(!validData()){
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            }else{
                if(validPassword()){
                    val user = UserSeller(0,name,"",email,password,"","","","","","","",0,date)
                    val intent= Intent(this, RegisterUserActivityTianguis::class.java)
                    intent.putExtra("nombreVendedor",user.nombreVendedor);
                    intent.putExtra("correo",user.correoVendedor)
                    intent.putExtra("date",user.fechaNacimientoVendedor)
                    intent.putExtra("password",user.contraseniaVendedor)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        binding.etBirthdate.setOnClickListener(){
            showDatePickerDialog()
        }

    }
    private fun validData(): Boolean{
        name = binding.etName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        date = binding.etBirthdate.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
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
        return validation
    }

    private fun validPassword(): Boolean{
        var validation: Boolean
        validation = false
        message = "Las contraseñas no son iguales"
        var confirmPassword = binding.etConfirmPassword.text.toString().trim()
        if(binding.etPassword.text.toString().trim().equals(confirmPassword)){
            validation = true
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
        binding.etBirthdate.setText(dateFormat.format(calendar.time))
    }
}