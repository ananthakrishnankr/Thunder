package com.akr.thunder

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.akr.thunder.network.CurrentWeather
import com.akr.thunder.network.Weather
import com.akr.thunder.weather.Degree
import kotlin.math.roundToInt

@BindingAdapter("temperature", "degree")
fun TextView.setRoundTemperature(weather: CurrentWeather?, degree: Degree) {
    text = context.getString(R.string.placeHolderDashs)
    weather?.let {
        val temperature = weather.temperature
        text = when (degree) {
            Degree.CELSIUS -> fahrenheitToCelsius(temperature)
            else -> temperature
        }.roundToInt().toString()
    }
}

@BindingAdapter("dataInPercentage")
fun TextView.setDataPercentage(data: Double) {
    text = context.getString(R.string.placeHolderDashs)
    if (data != 0.0) {
        text = data.times(100).roundToInt().toString().plus("%")
    }
}

@BindingAdapter("formatTime")
fun TextView.setFormattedTime(weather: Weather?) {
    text = context.getString(R.string.placeHolderDots)
    weather?.let {
        text = "At ${convertLongToDateString(it.currentWeather.time, it.timezone)} it will be"
    }
}

@BindingAdapter("summary")
fun TextView.setSummary(weather: CurrentWeather?) {
    text = context.getString(R.string.getting_current_weather)
    weather?.let {
        text = it.summary
    }
}

@BindingAdapter("degreeIconDrawable")
fun setDegreeIconDrawable(iconImageView: ImageView, degree: Degree) {
    iconImageView.setImageResource(
        when (degree) {
            Degree.FAHRENHEIT -> R.drawable.ic_fahrenheit
            else -> R.drawable.ic_celsius
        }
    )

}

@BindingAdapter("weatherIconDrawable")
fun setWeatherIconDrawable(iconImageView: ImageView, weather: CurrentWeather?) {
    weather?.let {
        iconImageView.setImageResource(
            when (it.icon) {
                "clear-day" -> R.drawable.clear_day
                "clear-night" -> R.drawable.clear_night
                "rain" -> R.drawable.rain
                "snow" -> R.drawable.snow
                "sleet" -> R.drawable.sleet
                "wind" -> R.drawable.wind
                "fog" -> R.drawable.fog
                "cloudy" -> R.drawable.cloudy
                "partly-cloudy-day" -> R.drawable.partly_cloudy
                "partly-cloudy-night" -> R.drawable.cloudy_night
                else -> R.drawable.clear_day
            }
        )
    }
}

@BindingAdapter("temperature")
fun TextView.setFormattedTemperature(weather: CurrentWeather?) {
    weather?.let {
        val temperature = weather.temperature
        text = fahrenheitToCelsius(temperature).roundToInt().toString().plus(" °C")
    }
}

@BindingAdapter("shortTime")
fun TextView.setFormattedShortTime(weather: CurrentWeather?) {
    text = context.getString(R.string.placeHolderDots)
    weather?.let {
        text = convertLongToHourString(it.time)
    }
}
