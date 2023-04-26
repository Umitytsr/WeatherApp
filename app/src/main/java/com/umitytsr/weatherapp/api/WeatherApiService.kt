package com.umitytsr.weatherapp.api

import com.umitytsr.weatherapp.constants.Const
import com.umitytsr.weatherapp.data.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    fun getProperties(
        @Query("latitude") latitude: Double = 40.20,
        @Query("longitude") longitude: Double = 29.06,
        @Query("current_weather") current_weather: Boolean = true,
        @Query("daily") daily: String ="weathercode,temperature_2m_max,temperature_2m_min",
        @Query("timezone") timeZone: String = "Europe%2FBerlin",
        @Query("temperature_unit") temperatureUnit: String = "celsius",
        @Query("forecast_days") forecastDays: Int = 14

    ): Call<WeatherResponse>

    companion object{
        fun create(): WeatherApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }


}