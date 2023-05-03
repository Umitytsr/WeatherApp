package com.umitytsr.weatherapp.network


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "daily_table")
data class Daily(
    @PrimaryKey(autoGenerate = true)
    val id3: Int = 1,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerializedName("time")
    val time: List<String>,
)