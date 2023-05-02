package com.umitytsr.weatherapp.api

import android.os.Environment
import com.umitytsr.weatherapp.util.Const
import com.umitytsr.weatherapp.network.WeatherResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    fun getProperties(
        @Query("latitude") latitude: Double = 40.7750,
        @Query("longitude") longitude: Double = 29.9480,
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "auto",
        @Query("daily") daily: String = "weathercode,temperature_2m_max,temperature_2m_min",
        @Query("temperature_unit") temperatureUnit: String = "celsius",
        @Query("forecast_days") forecastDays: Int = 14

    ): Call<WeatherResponse>

    companion object{
        fun create(): WeatherApiService{
            val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val cacheSize =(5 * 1024 * 1024).toLong()
            val myCache = Cache(Environment.getDataDirectory(),cacheSize)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(myCache)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }


}