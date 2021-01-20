package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> = _shoes

    init {
        _shoes.value = mutableListOf<Shoe>()
    }

    companion object {
        fun makeShoeInstance(): Shoe = Shoe("", 0.0, "", "")
    }

    fun addShoe(shoe: Shoe) {
        _shoes.value?.add(0, shoe)
    }

    fun addShoes(shoes: List<Shoe>) {
        _shoes.value?.addAll(0, shoes)
    }
}
