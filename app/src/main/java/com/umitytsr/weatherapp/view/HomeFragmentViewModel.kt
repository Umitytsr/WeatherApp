package com.umitytsr.weatherapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umitytsr.weatherapp.api.WeatherApiService
import com.umitytsr.weatherapp.data.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel: ViewModel() {

    private val weatherApiService = WeatherApiService.create()

    private val _properties = MutableLiveData<WeatherResponse>()
    val properties : LiveData<WeatherResponse> = _properties

    fun getWeatherCall(){
        weatherApiService.getProperties().enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful){
                    _properties.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable){

            }
        })
    }

}