package com.example.weatherforecast.api



data class ForecastModel(
    val location: Location,
    val forecast: Forecast
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avgtemp_c: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int
)

data class Hour(
    val time: String,
    val temp_c: Double,
    val condition: Condition
)
