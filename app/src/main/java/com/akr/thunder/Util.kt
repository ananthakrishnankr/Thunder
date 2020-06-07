package com.akr.thunder

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long, timeZone: String): String {
    val formatter = SimpleDateFormat("h:mm a")
    formatter.timeZone = TimeZone.getTimeZone(timeZone)
    return formatter.format(Date(systemTime * 1000))
}

@SuppressLint("SimpleDateFormat")
fun convertLongToHourString(systemTime: Long): String {
    val formatter = SimpleDateFormat("h a")
    val date = Date(systemTime * 1000)
    return formatter.format(date)
}

fun fahrenheitToCelsius(temperature: Double): Double {
    return ((temperature - 32.0f) * 5.0f) / 9.0f
}