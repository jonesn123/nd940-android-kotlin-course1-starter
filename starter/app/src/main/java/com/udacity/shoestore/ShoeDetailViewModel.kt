package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel : ViewModel() {
    var name: String = ""
    var size: String = ""
    var company: String = ""
    var description: String = ""

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun getShoe(): Shoe? {
        if (validateData()) {
            return Shoe(name, size.toDouble(), company, description)
        } else {
            _error.value = true
        }
        return null

    }

    private fun validateData() = name.isNotEmpty() &&
            size.isNotEmpty() &&
            company.isNotEmpty() &&
            description.isNotEmpty()
}