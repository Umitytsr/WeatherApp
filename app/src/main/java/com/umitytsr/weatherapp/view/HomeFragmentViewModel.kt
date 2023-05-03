package com.umitytsr.weatherapp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umitytsr.weatherapp.api.WeatherApiService
import com.umitytsr.weatherapp.data.local.WeatherDB
import com.umitytsr.weatherapp.network.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel(app : Application): AndroidViewModel(app) {

    private val weatherApiService = WeatherApiService.create()
    private val weatherDb = WeatherDB.getInstance(app.applicationContext)

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    fun getWeatherCall(){
        weatherApiService.getProperties().enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                weatherResponse: Response<WeatherResponse>
            ) {
                if (weatherResponse.isSuccessful) {
                    val response = weatherResponse.body()
                    _weatherData.value = response
                    Thread(Runnable {
                        weatherDb.weatherDao().deleteAll()
                        response?.let { weatherDb.weatherDao().insert(it) }
                    }).start()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable){
                Thread(Runnable {
                    val weatherList = weatherDb.weatherDao().getAll()
                    _weatherData.postValue(weatherList)
                }).start()
            }
        })
    }

}