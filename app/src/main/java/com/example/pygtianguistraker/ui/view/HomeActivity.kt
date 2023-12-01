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
import com.example.pygtianguistraker.ui.view.fragment.SettingsFragment

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: HomeActivityBinding
    private var typeUser: String = "buyer"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HometabfragmentAds())

        when (typeUser) {
            "buyer" -> {
                binding.bottomNavigationView.menu.findItem(R.id.favorite).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.home).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.User).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.separates).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.settings).isVisible = true
            }
            "seller" -> {
                binding.bottomNavigationView.menu.findItem(R.id.favorite).isVisible = false
                binding.bottomNavigationView.menu.findItem(R.id.home).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.User).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.separates).isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.settings).isVisible = true
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setFragment(HometabfragmentAds())
                R.id.favorite -> setFragment(ConsultActivityFavProducts())
                R.id.separates -> setFragment(ConsultActivityListReservedProducts())
                R.id.settings -> setFragment(SettingsFragment())
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

