package com.example.simplesplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SimpleSplashViewModel : ViewModel() {

    private val _isSplashScreenVisible = MutableStateFlow(true)
    val isSplashScreenVisible : StateFlow<Boolean> = _isSplashScreenVisible.asStateFlow()

    init {
        viewModelScope.launch{
            delay(2000)
            _isSplashScreenVisible.value = false
        }
    }

}