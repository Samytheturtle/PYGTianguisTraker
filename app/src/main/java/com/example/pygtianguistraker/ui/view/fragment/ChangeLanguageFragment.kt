package com.example.pygtianguistraker.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.ui.view.RegisterUserActivitySeller
import com.example.pygtianguistraker.ui.view.RegisterUserActivitybuyer

class ChangeLanguageFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_language, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val spanishLanguage = view.findViewById<Button>(R.id.buttonSpanish)
        val englishLanguage = view.findViewById<Button>(R.id.buttonEnglish)

        spanishLanguage.setOnClickListener {

        }
        englishLanguage.setOnClickListener {

        }
    }
}