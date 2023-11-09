package com.example.pygtianguistraker.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pygtianguistraker.data.model.UserSeller

class RegisterUserSellerViewModel: ViewModel() {
    val UserSeller = MutableLiveData<UserSeller>()
}