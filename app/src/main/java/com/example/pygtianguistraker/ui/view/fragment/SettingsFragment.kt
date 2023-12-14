
package com.example.pygtianguistraker.ui.view.fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.ui.view.ConsultActivityListReviews
import com.example.pygtianguistraker.ui.view.ConsultActivityListSellProducts
import com.example.pygtianguistraker.ui.view.CreateReviews
import com.example.pygtianguistraker.ui.view.LoginActivity
import java.util.Locale

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonChangeLanguage = view.findViewById<Button>(R.id.buttonChangeLenguage)
        val buttonSellProducts = view.findViewById<Button>(R.id.ButtonSellProducts)
        val buttonConsultReviews = view.findViewById<Button>(R.id.buttonConsultReviews)
        val buttonCreateReview = view.findViewById<Button>(R.id.buttonCreateReview)

        buttonChangeLanguage.setOnClickListener {
            // Cambiar entre inglés (en) y español (es)
            val currentLanguage = getCurrentLanguage() // Método que necesitas crear
            val newLanguage = if (currentLanguage == "es") "en" else "es"
            changeLanguage(newLanguage)

        }


        buttonSellProducts.setOnClickListener {
            val intent = Intent(activity, ConsultActivityListSellProducts::class.java)
            startActivity(intent)
        }

        buttonConsultReviews.setOnClickListener {
            val intent = Intent(activity, ConsultActivityListReviews::class.java)
            startActivity(intent)
        }

        buttonCreateReview.setOnClickListener{
            val intent = Intent(activity, CreateReviews::class.java)
            startActivity(intent)
        }


    }
    private fun setFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutSeller, fragment)
        fragmentTransaction.commit()
    }
    private fun saveLanguageSelection(language: String) {
        activity?.let {
            Toast.makeText(it,getString(R.string.ToastAlertLenguaje), Toast.LENGTH_SHORT).show()
            val preferences = activity?.getSharedPreferences("AppSettings", Context.MODE_PRIVATE) ?: return
            preferences.edit().putString("Language", language).apply()
        }

    }
    private fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = activity?.resources
        val config = resources?.configuration
        config?.setLocale(locale)
        resources?.updateConfiguration(config, resources.displayMetrics)

        saveLanguageSelection(languageCode)

        // Reiniciar actividad para aplicar cambios
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.finish()
        startActivity(intent)

    }

    private fun getCurrentLanguage(): String {
        val preferences = activity?.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        return preferences?.getString("Language", "es") ?: "es" // "es" es el valor por defecto
    }

}


