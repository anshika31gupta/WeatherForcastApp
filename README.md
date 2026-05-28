markdown# 🌦️ Weather Forecast App

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)
![API](https://img.shields.io/badge/WeatherAPI.com-00B4D8?style=for-the-badge)

A beautiful real-time weather app with **Ocean Green UI**, 
5-day forecast, and clean MVVM architecture.

</div>

---

## 📱 Screenshots

| Home Screen | Current Weather | 5-Day Forecast |
|:-----------:|:---------------:|:--------------:|
| <img width="1364" height="728" alt="weatherForcasteApp" src="https://github.com/user-attachments/assets/2155ee47-513e-46d5-99ea-de046b331536" />


---

## ✨ Features

- 🔍 **City Search** — Search weather of any city worldwide
- 🌡️ **Real-time Weather** — Current temperature & condition
- 📅 **5-Day Forecast** — Daily max/min temp with rain probability
- 💧 **Detailed Stats** — Humidity, Wind Speed, UV Index, Precipitation
- 🕐 **Local Time & Date** — Based on searched city's timezone
- 🎨 **Ocean Green UI** — Beautiful gradient with glassmorphism cards
- 🏗️ **MVVM Architecture** — Clean, maintainable code structure

---

## 🛠️ Tech Stack

| Technology | Purpose |
|:-----------|:--------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern declarative UI |
| **Retrofit + Gson** | REST API networking |
| **WeatherAPI.com** | Real-time weather data source |
| **LiveData + ViewModel** | MVVM state management |
| **Coil** | Async image loading for weather icons |
| **Kotlin Coroutines** | Async API calls |

---

## 🏗️ Project Structure
com.example.weatherforecast
├── api/
│   ├── WeatherApi.kt           # Retrofit API interface
│   ├── RetrofitInstance.kt     # Retrofit singleton setup
│   ├── WeatherModel.kt         # Current weather data model
│   ├── ForecastModel.kt        # 5-day forecast data model
│   ├── NetworkResponse.kt      # Sealed class (Success/Error/Loading)
│   └── Constant.kt             # API configuration
├── ui/theme/
│   └── WeatherViewModal.kt     # ViewModel with LiveData
└── WeatherPage.kt              # Main UI (Ocean Green theme)

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Android device/emulator with API 26+
- Free API key from [WeatherAPI.com](https://www.weatherapi.com/)

### Installation

1. **Clone the repository**
```bash
   git clone https://github.com/anshika31gupta/Weather-App-Jetpack-Compose.git
   cd Weather-App-Jetpack-Compose
```

2. **Add your API key**
   
   Open `app/src/main/java/com/example/weatherforecast/api/Constant.kt`:
```kotlin
   object Constant {
       var Apikey = "YOUR_API_KEY_HERE"
   }
```

3. **Build & Run**
   
   Open in Android Studio → Click ▶️ Run

---

## 📥 Download APK

[![Download APK](https://img.shields.io/badge/Download-APK-brightgreen?style=for-the-badge&logo=android)](https://github.com/anshika31gupta/Weather-App-Jetpack-Compose/releases/latest)

> Direct install karo — no Play Store needed!

---

## 📋 Requirements

- Android **8.0+** (API level 26)
- Active **internet connection**
- ~5 MB storage

---

## 🔒 Security Note

API key is **not hardcoded** in this repo.  
Get your free key at [weatherapi.com](https://www.weatherapi.com/) and add it to `Constant.kt`.

---

## 👩‍💻 Developer

<div align="center">

**Anshika Gupta**  
B.Tech Electronics & Communication Engineering  
PSIT Kanpur (AKTU) | 2023–2027

[![GitHub](https://img.shields.io/badge/GitHub-anshika31gupta-black?style=for-the-badge&logo=github)](https://github.com/anshika31gupta)

</div>
