package com.umitytsr.weatherapp.network


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "current_weather_table")
data class CurrentWeather(
    @PrimaryKey(autoGenerate = true)
    val id2: Int = 1,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time2: String,
    @SerializedName("weathercode")
    val weathercode: Int,
    @SerializedName("winddirection")
    val winddirection: Double,
    @SerializedName("windspeed")
    val windspeed: Double
)