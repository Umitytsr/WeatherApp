package com.umitytsr.weatherapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.weatherapp.R
import com.umitytsr.weatherapp.util.Const
import com.umitytsr.weatherapp.network.WeatherResponse
import com.umitytsr.weatherapp.databinding.ItemRowBinding
import com.umitytsr.weatherapp.databinding.ItemRowTodayBinding


class WeatherModulAdapter(weatherResponse: WeatherResponse):
    RecyclerView.Adapter<WeatherModulAdapter.WeatherViewHolder>() {

    private val current_weather = weatherResponse.currentWeather
    private val dates = weatherResponse.daily.time
    private val minTemp = weatherResponse.daily.temperature2mMin
    private val maxTemp = weatherResponse.daily.temperature2mMax

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(
            date: String,
            minT: Double,
            maxT: Double
        ){
            if (adapterPosition == Const.ITEM_ROW_TODAY){
                val binding = ItemRowTodayBinding.bind(itemView)
                binding.today.text = date

            }else{
                val binding = ItemRowBinding.bind(itemView)
                with(binding){
                    dateTime.text = date
                    minTemp.text = minT.toString()
                    maxTemp.text = maxT.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = when(viewType){
            Const.ITEM_ROW_TODAY -> layoutInflater.inflate(R.layout.item_row_today,parent,false)
            Const.ITEM_ROW -> layoutInflater.inflate(R.layout.item_row,parent,false)
            else -> throw java.lang.IllegalArgumentException()
        }
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(
            date = dates.get(position),
            minT = minTemp.get(position),
            maxT = maxTemp.get(position)
        )
    }

    override fun getItemCount(): Int {
        return dates.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position ==0){
            Const.ITEM_ROW_TODAY
        }else{
            Const.ITEM_ROW
        }
    }
}