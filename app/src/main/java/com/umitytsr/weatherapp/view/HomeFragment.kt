package com.umitytsr.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.umitytsr.weatherapp.network.WeatherResponse
import com.umitytsr.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.btnApi.setOnClickListener {
            viewModel.getWeatherCall()
        }

        initObserver()
        return binding.root
    }

    fun initObserver(){
        viewModel.weatherData.observe(viewLifecycleOwner){
            initRecyclerView(it)
        }
    }

    fun initRecyclerView(weatherResponse: WeatherResponse){
        val _adapter = WeatherModulAdapter(weatherResponse)
        binding.recyclerView.adapter = _adapter
    }
}