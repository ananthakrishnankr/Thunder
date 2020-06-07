package com.akr.thunder.hourly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akr.thunder.network.CurrentWeather

class HourlyViewModel(hourlyData: Array<CurrentWeather>) : ViewModel() {

    private val _hourlyList = MutableLiveData<List<CurrentWeather>>()
    val hourlyList: LiveData<List<CurrentWeather>>
        get() = _hourlyList

    init {
        _hourlyList.value = hourlyData.toList()
    }
}