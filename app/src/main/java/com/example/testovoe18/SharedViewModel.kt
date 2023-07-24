package com.example.testovoe18

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedElem: MutableLiveData<List<Card>> = MutableLiveData()
    val selectedElem: LiveData<List<Card>> = _selectedElem

    fun getElem(): LiveData<List<Card>> {
        return _selectedElem.value?.reversed()?.let { MutableLiveData(it) } ?: _selectedElem
    }

    fun addElem(card: Card) {
        val currentList = _selectedElem.value?.toMutableList() ?: mutableListOf()
        currentList.add(card)
        _selectedElem.value = currentList
    }

    fun removeAllElem() {
        _selectedElem.value = emptyList()
    }
}