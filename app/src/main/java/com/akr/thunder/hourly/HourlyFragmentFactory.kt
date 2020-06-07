package com.akr.thunder.hourly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akr.thunder.network.CurrentWeather
import com.akr.thunder.weather.WeatherViewModel


class HourlyFragmentFactory(private val hourlyData: Array<CurrentWeather>) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HourlyViewModel::class.java)) {
            return HourlyViewModel(hourlyData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
