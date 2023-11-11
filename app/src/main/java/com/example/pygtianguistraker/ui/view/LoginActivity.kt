package com.example.pygtianguistraker.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pygtianguistraker.databinding.LoginActivityBinding
import com.example.pygtianguistraker.ui.view.fragment.UserPopRegisterSelectFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Loginbutton.setOnClickListener{
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener{
            val dialogFragment = UserPopRegisterSelectFragment()
            dialogFragment.show(supportFragmentManager, "PopUp")
        }
    }
}