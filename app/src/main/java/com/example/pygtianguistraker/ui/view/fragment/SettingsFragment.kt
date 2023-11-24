
package com.example.pygtianguistraker.ui.view.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.ui.view.ConsultActivityListReviews
import com.example.pygtianguistraker.ui.view.ConsultActivityListSellProducts

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

        buttonChangeLanguage.setOnClickListener {
            setFragment(ChangeLanguageFragment())
        }

        buttonSellProducts.setOnClickListener {
            val intent = Intent(activity, ConsultActivityListSellProducts::class.java)
            startActivity(intent)
        }

        buttonConsultReviews.setOnClickListener {
            val intent = Intent(activity, ConsultActivityListReviews::class.java)
            startActivity(intent)
        }


    }
    private fun setFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutSeller, fragment)
        fragmentTransaction.commit()
    }

}


