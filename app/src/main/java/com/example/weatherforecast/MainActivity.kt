package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast.ui.theme.WeatherForecastTheme
import com.example.weatherforecast.ui.theme.WeatherViewModal


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var WeatherViewModal = ViewModelProvider(this)[WeatherViewModal::class.java]
        enableEdgeToEdge()
        setContent {
            WeatherForecastTheme {
                Surface (
                    modifier= Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    WeatherPage(WeatherViewModal)
                }

            }

        }
    }
}


