package com.example.pygtianguistraker.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pygtianguistraker.R
import com.example.pygtianguistraker.databinding.HomeActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityFavProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityListReservedProducts
import com.example.pygtianguistraker.ui.view.fragment.ConsultActivityProfile
import com.example.pygtianguistraker.ui.view.fragment.HometabfragmentAds
import com.example.pygtianguistraker.ui.view.fragment.SettingsActivity

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: HomeActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HometabfragmentAds())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setFragment(HometabfragmentAds())
                R.id.favorite -> setFragment(ConsultActivityFavProducts())
                R.id.separates -> setFragment(ConsultActivityListReservedProducts())
                R.id.settings -> setFragment(SettingsActivity())
                R.id.User -> setFragment(ConsultActivityProfile())
                else -> { }
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutSeller, fragment)
        fragmentTransaction.commit()
    }
}

