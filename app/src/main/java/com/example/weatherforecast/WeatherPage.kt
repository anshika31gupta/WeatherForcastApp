package com.example.weatherforecast

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherforecast.api.ForecastDay
import com.example.weatherforecast.api.NetworkResponse
import com.example.weatherforecast.api.WeatherModel
import com.example.weatherforecast.ui.theme.WeatherViewModal

val OceanDark  = Color(0xFF0A3D2E)
val OceanMid   = Color(0xFF0D6B52)
val OceanLight = Color(0xFF1A8A6A)
val CardBg     = Color(0x26FFFFFF)
val TextWhite  = Color.White
val TextMuted  = Color(0xBBFFFFFF)
val TextFaint  = Color(0x80FFFFFF)

@Composable
fun WeatherPage(viewModel: WeatherViewModal) {
    var city by remember { mutableStateOf("") }
    val weatherResult  = viewModel.WeatherReasponse.observeAsState()
    val forecastResult = viewModel.forecastResponse.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(OceanDark, OceanMid, OceanLight)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Fix 1 — OutlinedTextField ko Box mein wrap kiya
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .background(CardBg)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Box(modifier = Modifier.weight(1f)) {  // ← weight Box pe
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = { Text("Search city...", color = TextFaint) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor   = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor     = TextWhite,
                            unfocusedTextColor   = TextWhite,
                            cursorColor          = TextWhite
                        ),
                        modifier = Modifier.fillMaxWidth()  // ← weight nahi
                    )
                }

                IconButton(onClick = { viewModel.getData(city) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = TextWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            val result = weatherResult.value
            when (result) {
                is NetworkResponse.loading -> {
                    Spacer(modifier = Modifier.height(100.dp))
                    CircularProgressIndicator(color = TextWhite)
                }
                is NetworkResponse.error -> {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = result.message,
                        color = Color(0xFFFF6B6B),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
                is NetworkResponse.Success -> {
                    WeatherMainContent(result.data)
                    Spacer(modifier = Modifier.height(16.dp))
                    val forecast = forecastResult.value
                    if (forecast is NetworkResponse.Success) {
                        ForecastSection(forecast.data.forecast.forecastday)
                    }
                }
                null -> {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = "Kisi bhi city ka weather search karo",
                        color = TextMuted,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun WeatherMainContent(data: WeatherModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = data.location.name,
                color = TextWhite,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = data.location.country,
                color = TextFaint,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Weather icon",
            modifier = Modifier.size(110.dp)
        )

        Text(
            text = "${data.current.temp_c}°C",
            color = TextWhite,
            fontSize = 80.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 88.sp
        )

        Text(
            text = data.current.condition.text,
            color = TextMuted,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Stats Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(CardBg)
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // ✅ Fix 2 — WeatherStat ko Row ke direct child banaya
                    // weight ab Column mein nahi, seedha Row mein hai
                    WeatherStat("Humidity", "${data.current.humidity}%",
                        Modifier.weight(1f))
                    StatDivider()
                    WeatherStat("Wind", "${data.current.wind_kph} km/h",
                        Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherStat("UV Index", "${data.current.uv}",
                        Modifier.weight(1f))
                    StatDivider()
                    WeatherStat("Precip", "${data.current.precip_mm} mm",
                        Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val parts = data.location.localtime.split(" ")
                    WeatherStat("Time", parts.getOrNull(1) ?: "N/A",
                        Modifier.weight(1f))
                    StatDivider()
                    WeatherStat("Date", parts.getOrNull(0) ?: "N/A",
                        Modifier.weight(1f))
                }
            }
        }
    }
}

// ✅ Fix 2 — modifier parameter add kiya
@Composable
fun WeatherStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier  // ← bahar se aata hai ab
    ) {
        Text(
            text = label.uppercase(),
            color = TextFaint,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = TextWhite,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun StatDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(36.dp)
            .background(Color(0x33FFFFFF))
    )
}

@Composable
fun ForecastSection(forecastDays: List<ForecastDay>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardBg)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "5-DAY FORECAST",
                color = TextFaint,
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                forecastDays.forEachIndexed { index, day ->
                    ForecastCard(day, isToday = index == 0)
                }
            }
        }
    }
}

@Composable
fun ForecastCard(day: ForecastDay, isToday: Boolean) {
    val cardAlpha = if (isToday) Color(0x40FFFFFF) else Color(0x1AFFFFFF)

    val dateParts = day.date.split("-")
    val months = listOf("","Jan","Feb","Mar","Apr","May",
        "Jun","Jul","Aug","Sep","Oct","Nov","Dec")
    val dateLabel = if (isToday) "Today"
    else "${months[dateParts[1].toInt()]} ${dateParts[2]}"

    Box(
        modifier = Modifier
            .width(75.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(cardAlpha)
            .padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = dateLabel, color = TextMuted, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(6.dp))
            AsyncImage(
                model = "https:${day.day.condition.icon}",
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${day.day.maxtemp_c.toInt()}°",
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${day.day.mintemp_c.toInt()}°",
                color = TextFaint,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0x26FFFFFF))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "${day.day.daily_chance_of_rain}%",
                    color = TextMuted,
                    fontSize = 10.sp
                )
            }
        }
    }
}