package com.umitytsr.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umitytsr.weatherapp.network.WeatherResponse

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_response")
    fun getAll(): WeatherResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherResponse: WeatherResponse)

    @Query("DELETE FROM weather_response")
    fun deleteAll()
}