package com.example.weatherforecast.api

import com.example.weatherforecast.ui.theme.WeatherViewModal
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private const val BaseUrl =  "https://api.weatherapi.com/v1/"
    private fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val weatherApi :WeatherApi= getInstance().create(WeatherApi::class.java)
}