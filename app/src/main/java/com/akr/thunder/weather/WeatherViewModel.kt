package com.akr.thunder.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akr.thunder.network.Weather
import com.akr.thunder.network.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, ERROR, DONE }
enum class Degree { CELSIUS, FAHRENHEIT }

class WeatherViewModel(private val latLng:String) : ViewModel() {

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus>
        get() = _status

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private val _degree = MutableLiveData<Degree>()
    val degree: LiveData<Degree>
        get() = _degree

    private val _navigateToHourlyWeather = MutableLiveData<Weather>()
    val navigateToHourlyWeather: LiveData<Weather>
        get() = _navigateToHourlyWeather

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _degree.value = Degree.CELSIUS
        getCurrentWeatherData(latLng)
    }

    private fun getCurrentWeatherData(latLng: String) {
        coroutineScope.launch {
            _status.value = WeatherApiStatus.LOADING
            var getWeatherDeferred = WeatherApi.retrofitService.getData(latLng)
            try {
                _status.value = WeatherApiStatus.LOADING
                var result = getWeatherDeferred.await()
                _status.value = WeatherApiStatus.DONE
                _weather.value = result
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
                _weather.value = null
            }
        }
    }

    fun refresh() {
        getCurrentWeatherData(latLng)
    }

    fun changeDegree() {
        _degree.value = when (_degree.value) {
            Degree.CELSIUS -> Degree.FAHRENHEIT
            else -> Degree.CELSIUS
        }

    }

    fun displayHourlyWeather() {
        _navigateToHourlyWeather.value = _weather.value
    }

    fun displayHourlyWeatherComplete() {
        _navigateToHourlyWeather.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}