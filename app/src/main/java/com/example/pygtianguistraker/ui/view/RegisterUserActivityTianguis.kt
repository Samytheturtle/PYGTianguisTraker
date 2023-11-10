package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.Tianguis
import com.example.pygtianguistraker.data.model.TianguisCollection
import com.example.pygtianguistraker.data.network.UserSellerApiClient
import com.example.pygtianguistraker.databinding.RegisterActivityTianguisBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RegisterUserActivityTianguis : AppCompatActivity() {

    private lateinit var binding:RegisterActivityTianguisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityTianguisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTianguis()

        binding.btCancelSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btRegisterSeller.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getTianguis() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<List<TianguisCollection>> = service.getTianguis()

        result.enqueue(object : Callback<List<TianguisCollection>>{
            override fun onResponse(
                call: Call<List<TianguisCollection>>,
                response: Response<List<TianguisCollection>>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Bien hecho", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TianguisCollection>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error en la llamada: ${t.message}", Toast.LENGTH_SHORT).show()
                println(t.message)
            }

        })
    }


}





