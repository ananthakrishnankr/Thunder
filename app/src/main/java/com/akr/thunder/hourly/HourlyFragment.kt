package com.akr.thunder.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akr.thunder.databinding.FragmentHourlyBinding
import com.akr.thunder.network.CurrentWeather

class HourlyFragment() : Fragment() {

    private lateinit var viewModel: HourlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHourlyBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // val hourlyData = HourlyFragmentArgs.fromBundle(requireArguments()).hourlyData
        val hourlyData = arguments?.getParcelableArray("hourlyData") as Array<CurrentWeather>

        viewModel = ViewModelProviders.of(this, HourlyFragmentFactory(hourlyData))
            .get(HourlyViewModel::class.java)
        binding.viewModel = viewModel

        val adapter = HourlyAdapter()
        binding.hourlyList.adapter = adapter

        viewModel.hourlyList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.reversed())
            }
        })

        return binding.root
    }
}