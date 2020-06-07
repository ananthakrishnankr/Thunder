package com.akr.thunder.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.fragment.findNavController
import com.akr.thunder.R
import com.akr.thunder.databinding.FragmentWeatherBinding
import com.akr.thunder.network.CurrentWeather

private const val BANGALORE_LATLNG = "12.9716,77.5946"

class WeatherFragment : Fragment() {

    private val viewModel by viewModels<WeatherViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWeatherBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            status?.let {
                when (status) {
                    WeatherApiStatus.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.refreshImageView.visibility = View.INVISIBLE
                    }
                    WeatherApiStatus.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.refreshImageView.visibility = View.VISIBLE
                    }
                    WeatherApiStatus.DONE -> {
                        binding.progressBar.visibility = View.GONE
                        binding.refreshImageView.visibility = View.VISIBLE
                    }
                }
            }
        })

        viewModel.navigateToHourlyWeather.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                /*this.findNavController().graph.findNode(R.id.action_showHourlyData)?.addArgument(
                    "allCategories", NavArgument.Builder()
                        .setType(NavType.ParcelableArrayType(CurrentWeather::class.java))
                        .setDefaultValue(it.hourly.data.toTypedArray())
                        .build()
                )*/

                this.findNavController().navigate(WeatherFragmentDirections
                    .actionShowHourlyData(it.hourly.data.toTypedArray()))
                viewModel.displayHourlyWeatherComplete()

            }
        })

        return binding.root
    }

    private fun getViewModelFactory(): WeatherViewModelFactory =
        WeatherViewModelFactory(BANGALORE_LATLNG)

}