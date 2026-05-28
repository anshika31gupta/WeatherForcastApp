package com.example.weatherforecast.api

sealed class NetworkResponse <out T>{
    data class Success<out T>(val data:T): NetworkResponse<T>()
    data class error(val message :String ): NetworkResponse<Nothing>()
    object loading: NetworkResponse<Nothing>()
}