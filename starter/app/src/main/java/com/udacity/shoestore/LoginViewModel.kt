package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var id = ""
    var password = ""

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun sign() : String? {
        if (validateLogin()) {
            return id
        } else {
            _error.value = true
        }
        return null
    }

    private fun validateLogin() =
        id.isNotEmpty() && password.isNotEmpty()

}