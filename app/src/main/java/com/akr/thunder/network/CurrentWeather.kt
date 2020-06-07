package com.akr.thunder.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class Weather(
    val timezone: String,
    @Json(name = "currently") val currentWeather: CurrentWeather,
    val hourly: Hourly
)

@Parcelize
data class CurrentWeather(
    val icon: String,
    val time: Long,
    val temperature: Double,
    val humidity: Double,
    @Json(name = "precipProbability") val precipChance: Double,
    val summary: String
) : Parcelable


data class Hourly(
    val data: List<CurrentWeather>
)