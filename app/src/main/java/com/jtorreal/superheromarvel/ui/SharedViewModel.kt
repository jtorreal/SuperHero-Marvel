package com.jtorreal.superheromarvel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> get() = _id

    fun saveId(id: Int?) {
        if (id != null) {
            _id.value = id
        }
    }
}