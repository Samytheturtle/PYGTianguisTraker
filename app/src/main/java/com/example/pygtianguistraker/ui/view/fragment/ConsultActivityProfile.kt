
package com.example.pygtianguistraker.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.ui.view.LoginActivity

class ConsultActivityProfile : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.consult_activity_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val editButton = view.findViewById<Button>(R.id.buttonEditInformationUser)
        val deleteButton = view.findViewById<Button>(R.id.buttondeleteUser)
        val logoutButton = view.findViewById<Button>(R.id.button3)

        editButton.setOnClickListener {
            // Acción para el botón "Editar"
            // Puedes abrir una nueva actividad o fragmento para la edición de información aquí
        }

        deleteButton.setOnClickListener {
            // Acción para el botón "Eliminar"
            // Puedes mostrar un diálogo de confirmación para eliminar la cuenta aquí
        }

        logoutButton.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

}