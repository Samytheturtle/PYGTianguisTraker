package com.example.pygtianguistraker.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.ui.view.RegisterUserActivitySeller
import com.example.pygtianguistraker.ui.view.RegisterUserActivitybuyer

class UserPopRegisterSelect : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_pop_register_select, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val ShowActivityRegisterSeller = view.findViewById<Button>(R.id.registerSeller)
        val ShowActivityRegisterBuyer = view.findViewById<Button>(R.id.registerBuyer)

        ShowActivityRegisterSeller.setOnClickListener {
            val intent = Intent(requireContext(),RegisterUserActivitySeller ::class.java)
            startActivity(intent)
            dismiss()
        }
        ShowActivityRegisterBuyer.setOnClickListener {
            val intent = Intent(requireContext(), RegisterUserActivitybuyer::class.java)
            startActivity(intent)
            dismiss()
        }
    }

}