package com.akr.thunder.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class WeatherViewModelFactory(
    private val latLng: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(latLng) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
