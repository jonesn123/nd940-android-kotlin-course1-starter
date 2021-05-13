package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val shoeList: ArrayList<Shoe> = arrayListOf(
        Shoe("Shoe A", 255.0, "Company A", "Description A"),
        Shoe("Shoe B", 270.0, "Company B", "Description B"),
        Shoe("Shoe C", 235.0, "Company C", "Description C")
    )

    private val _shoes: MutableLiveData<List<Shoe>> = MutableLiveData()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun fetchShoes() {
        _shoes.value = shoeList
    }

    fun addShoe(shoe: Shoe) {
        shoeList.add(shoe)
        _shoes.value = shoeList
    }

}