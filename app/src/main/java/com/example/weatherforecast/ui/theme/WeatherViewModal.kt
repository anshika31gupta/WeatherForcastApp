package com.example.weatherforecast.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.api.Constant
import com.example.weatherforecast.api.ForecastModel
import com.example.weatherforecast.api.NetworkResponse
import com.example.weatherforecast.api.RetrofitInstance
import com.example.weatherforecast.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModal : ViewModel() {

    private var weatherApi = RetrofitInstance.weatherApi

    private var _weatherResponse = MutableLiveData<NetworkResponse<WeatherModel>>()
    var WeatherReasponse: LiveData<NetworkResponse<WeatherModel>> = _weatherResponse

    private var _forecastResponse = MutableLiveData<NetworkResponse<ForecastModel>>()
    var forecastResponse: LiveData<NetworkResponse<ForecastModel>> = _forecastResponse

    fun getData(city: String) {
        Log.i("city name:", city)
        _weatherResponse.value = NetworkResponse.loading
        _forecastResponse.value = NetworkResponse.loading

        viewModelScope.launch {
            try {
                // Current weather
                val response = weatherApi.getWeather(Constant.Apikey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResponse.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResponse.value = NetworkResponse.error("Failed to load weather")
                }

                // Forecast
                val forecastResp = weatherApi.getForecast(Constant.Apikey, city, 5)
                if (forecastResp.isSuccessful) {
                    forecastResp.body()?.let {
                        _forecastResponse.value = NetworkResponse.Success(it)
                    }
                } else {
                    _forecastResponse.value = NetworkResponse.error("Failed to load forecast")
                }

            } catch (e: Exception) {
                Log.e("WeatherError:", e.message.toString())
                _weatherResponse.value = NetworkResponse.error("Error: ${e.message}")
                _forecastResponse.value = NetworkResponse.error("Error: ${e.message}")
            }
        }
    }
}