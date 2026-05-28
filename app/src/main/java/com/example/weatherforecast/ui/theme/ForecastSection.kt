package com.example.weatherforecast.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherforecast.api.ForecastDay

@Composable
fun ForecastSection(forecastDays: List<ForecastDay>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "5-Day Forecast",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(forecastDays) { day ->
                ForecastCard(day)
            }
        }
    }
}

@Composable
fun ForecastCard(day: ForecastDay) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Date — "2024-01-15" se "Jan 15" bana do
            val dateParts = day.date.split("-")
            val months = listOf("","Jan","Feb","Mar","Apr","May",
                "Jun","Jul","Aug","Sep","Oct","Nov","Dec")
            val monthName = months[dateParts[1].toInt()]

            Text(
                text = "$monthName ${dateParts[2]}",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            AsyncImage(
                model = "https:${day.day.condition.icon}",
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Text(
                text = "${day.day.maxtemp_c}°",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${day.day.mintemp_c}°",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "🌧 ${day.day.daily_chance_of_rain}%",
                fontSize = 12.sp,
                color = Color.Blue
            )
        }
    }
}