package com.umitytsr.weatherapp.network


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.umitytsr.weatherapp.network.CurrentWeather
import com.umitytsr.weatherapp.network.Daily
import com.umitytsr.weatherapp.network.DailyUnits
@Entity("weather_response")
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,

    @SerializedName("current_weather")
    @Embedded
    val currentWeather: CurrentWeather,

    @SerializedName("daily")
    @Embedded
    val daily: Daily,

)