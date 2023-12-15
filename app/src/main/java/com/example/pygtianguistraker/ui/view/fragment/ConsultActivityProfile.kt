
package com.example.pygtianguistraker.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.core.Helper
import com.example.pygtianguistraker.data.model.AuthResponse
import com.example.pygtianguistraker.data.model.Tianguis
import com.example.pygtianguistraker.data.model.UserBuyer
import com.example.pygtianguistraker.data.model.UserSeller
import com.example.pygtianguistraker.data.network.UserBuyerApiClient
import com.example.pygtianguistraker.data.network.UserSellerApiClient
import com.example.pygtianguistraker.ui.view.LoginActivity
import com.example.pygtianguistraker.ui.view.ModifyActivityProfileBuyer
import com.example.pygtianguistraker.ui.view.ModifyActivityProfileSeller
import com.example.pygtianguistraker.ui.view.ShowDetailsSeller
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultActivityProfile : Fragment(){
    private val PREFS_NAME = "my_app_information"

    private lateinit var authorizationHeader: String
    private lateinit var userType: String
    private var id: Int = 0
    private var seller = UserSeller()
    private var buyer = UserBuyer()
    private lateinit var tianguis: Tianguis

    private lateinit var tvUserInformation: TextView
    private lateinit var tvUserDate: TextView
    private lateinit var tvUserEmailInformation: TextView
    private lateinit var tvTianguisLocate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.consult_activity_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        tvUserEmailInformation = view.findViewById(R.id.textUserEmailInformation)
        tvUserInformation = view.findViewById(R.id.textUserInformation)
        tvUserDate = view.findViewById(R.id.textUserDate)
        tvTianguisLocate = view.findViewById(R.id.textTianguisLocate)

        val editButton = view.findViewById<Button>(R.id.buttonEditInformationUser)
        val showInformationButton = view.findViewById<Button>(R.id.buttonShowDetailsUser)
        val logoutButton = view.findViewById<Button>(R.id.button3)

        val prefs = getSharedPreferences()
        val dataSaved = prefs.getString("datos-usuario", null)

        if (dataSaved != null) {

            val gson = Gson()
            val user = gson.fromJson(dataSaved, AuthResponse::class.java)
            authorizationHeader = user.token
            id = user.id
            userType = user.user

            when (userType) {

                "Comprador" -> {

                    showInformationButton.isVisible = false
                    getUserBuyer()
                }

                "Vendedor" -> {

                    showInformationButton.isVisible = true
                    getUserSeller()
                }
            }
        }

        showInformationButton.setOnClickListener {

            val intent = Intent(activity, ShowDetailsSeller::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        editButton.setOnClickListener {

            when (userType) {

                "Comprador" -> {

                    showEditBuyer()
                }

                "Vendedor" -> {

                    showEditSeller()
                }
            }
        }

        logoutButton.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun showEditBuyer() {

        val intent = Intent(activity, ModifyActivityProfileBuyer::class.java)
        intent.putExtra("correoUsuario", buyer.correoUsuario)
        intent.putExtra("contraseniaUsuario", buyer.contraseniaUsuario)
        intent.putExtra("nombreComprador", buyer.nombreComprador)
        intent.putExtra("ubicacionComprador", buyer.ubicacionComprador)
        intent.putExtra("fechaNacimientoComprador", buyer.fechaNacimientoComprador)
        startActivity(intent)
    }

    private fun showEditSeller() {

        val intent = Intent(activity, ModifyActivityProfileSeller::class.java)
        intent.putExtra("correo", seller.correoUsuario)
        intent.putExtra("nombreVendedor", seller.nombreVendedor)
        intent.putExtra("calificacionVendedor", seller.calificacionVendedor)
        intent.putExtra("horarioLunesVendedor", seller.horarioLunesVendedor)
        intent.putExtra("horarioMartesVendedor", seller.horarioMartesVendedor)
        intent.putExtra("horarioMiercolesVendedor", seller.horarioMiercolesVendedor)
        intent.putExtra("horarioJuevesVendedor", seller.horarioJuevesVendedor)
        intent.putExtra("horarioViernesVendedor", seller.horarioViernesVendedor)
        intent.putExtra("horarioSabadoVendedor", seller.horarioSabadoVeneddor)
        intent.putExtra("horarioDomingoVendedor", seller.horarioDomingoVendedor)
        intent.putExtra("fechaNacimientoVendedor", seller.fechaNacimientoVendedor)
        intent.putExtra("idTianguisVendedor", seller.idTianguisVendedor)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearSharedPreferences()
    }

    private fun clearSharedPreferences() {
        val prefs = getSharedPreferences()
        val editor = prefs.edit()
        editor.remove("datos_usuario")
        editor.apply()
    }

    private fun getSharedPreferences(): SharedPreferences {
        return requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun getUserSeller() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<UserSeller> = service.getSeller(authorizationHeader, id)
        result.enqueue(object: Callback<UserSeller>{

            override fun onResponse(call: Call<UserSeller>, response: Response<UserSeller>) {

                if (response.isSuccessful) {

                    seller = response.body()!!
                    Log.d("Vendedor", seller.toString())
                    tvUserEmailInformation.text = seller.correoUsuario
                    tvUserDate.text = seller.fechaNacimientoVendedor
                    tvUserInformation.text = seller.nombreVendedor
                    getTianguis(seller.idTianguisVendedor)

                } else {

                    when (response.code()) {

                        401 -> {
                            Toast.makeText(requireContext(), "Error de autorización", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(requireContext(), "Error del servidor", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserSeller>, t: Throwable) {

                Log.e("ConsultSeller", "Error en la solicitud: ${t.message}")
                Toast.makeText(requireContext(), "No se puede consultar tu información.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTianguis(idTianguis: Int) {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserSellerApiClient::class.java)
        val result: Call<Tianguis> = service.getOneTianguis(idTianguis)
        result.enqueue(object: Callback<Tianguis>{

            override fun onResponse(call: Call<Tianguis>, response: Response<Tianguis>) {

                if (response.isSuccessful) {

                    tianguis = response.body()!!
                    Log.d("Tianguis", tianguis.toString())
                    tvTianguisLocate.text = tianguis.direccionTianguis

                } else {

                    when (response.code()) {

                        401 -> {
                            Toast.makeText(requireContext(), "Error de autorización", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(requireContext(), "Error del servidor", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Tianguis>, t: Throwable) {

                Log.e("ConsultTianguis", "Error en la solicitud: ${t.message}")
                Toast.makeText(requireContext(), "No se puede consultar tu información detallada.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getUserBuyer() {
        val retrofit = Helper.getRetrofit()
        val service = retrofit.create(UserBuyerApiClient::class.java)
        val result: Call<UserBuyer> = service.getBuyer(authorizationHeader, id)
        result.enqueue(object: Callback<UserBuyer>{

            override fun onResponse(call: Call<UserBuyer>, response: Response<UserBuyer>) {

                if (response.isSuccessful) {

                    buyer = response.body()!!
                    Log.d("Comprador", buyer.toString())
                    tvUserEmailInformation.text = buyer.correoUsuario
                    tvUserDate.text = buyer.fechaNacimientoComprador
                    tvUserInformation.text = buyer.nombreComprador
                    tvTianguisLocate.text = buyer.ubicacionComprador

                } else {

                    when (response.code()) {

                        401 -> {
                            Toast.makeText(requireContext(), "Error de autorización", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(requireContext(), "Error del servidor", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserBuyer>, t: Throwable) {

                Log.e("ConsultBuyer", "Error en la solicitud: ${t.message}")
                Toast.makeText(requireContext(), "No se puede consultar tu información.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}