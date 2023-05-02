package com.umitytsr.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.umitytsr.weatherapp.network.CurrentWeather
import com.umitytsr.weatherapp.network.Daily
import com.umitytsr.weatherapp.network.WeatherResponse
import com.umitytsr.weatherapp.util.DatabaseConverter

@Database(
    entities = [WeatherResponse::class, CurrentWeather::class, Daily::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class WeatherDB(): RoomDatabase(){
    abstract fun weatherDao(): WeatherDao

    companion object{
        @Volatile
        private var instance: WeatherDB? = null

        fun getInstance(context: Context): WeatherDB {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDB::class.java,
                    "weather_response_database"
                ).build()
                instance = database
                database
            }
        }
    }
}