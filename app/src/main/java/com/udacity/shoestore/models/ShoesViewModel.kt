package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> = _shoes

    private val _predefinedDataIsLoaded = MutableLiveData<Boolean>(false)
    val predefinedDataIsLoaded: LiveData<Boolean> = _predefinedDataIsLoaded

    private val repository = FakeShoeRepository()

    init {
        _shoes.value = mutableListOf()
    }

    companion object {
        fun makeShoeInstance(): Shoe = Shoe("", 0.0, "", "")
    }

    fun addShoe(shoe: Shoe) {
        _shoes.value?.add(0, shoe)
        _shoes.forceRefresh()
    }

    fun addPredefinedShoes() {
        _shoes.value?.addAll(repository.getPredefinedShoes())
        _predefinedDataIsLoaded.value = true
        _shoes.forceRefresh()
    }

    fun clearShoesList() {
        _shoes.value?.clear()
        _predefinedDataIsLoaded.value = false
        _shoes.forceRefresh()
    }

    fun logoutUser() {
        clearShoesList()
    }
}

fun <T> MutableLiveData<T>.forceRefresh() {
    this.value = this.value
}
