package com.example.pygtianguistraker.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ads_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reserveButton = view.findViewById<Button>(R.id.reserveButton)
        val detailsButton = view.findViewById<Button>(R.id.detailsButton)
        val favoriteButton = view.findViewById<ImageButton>(R.id.favoriteButton)
        // Controlador para el botón de reservar
        Log.d("funciona", "HOLAAAAAAAAAAAAAAA")
        reserveButton.setOnClickListener {
            Log.d("funciona", "Reservar clickeado")
            showToast("Reservar clickeado")
        }

        detailsButton.setOnClickListener {
            Log.d("funciona", "Detalles clickeados")
            showToast("Detalles clickeados")
        }

        favoriteButton.setOnClickListener {
            Log.d("funciona", "Favorito clickeado")
            showToast("Favorito clickeado")
        }

    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 150) // Ajusta la posición vertical según tus necesidades
        toast.show()
    }
}
