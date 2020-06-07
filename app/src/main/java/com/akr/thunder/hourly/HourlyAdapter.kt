package com.akr.thunder.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akr.thunder.databinding.HourlyListItemBinding
import com.akr.thunder.network.CurrentWeather


class HourlyAdapter : ListAdapter<CurrentWeather,
        HourlyAdapter.HourlyDataViewHolder>(HourlyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyDataViewHolder {
        return HourlyDataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HourlyDataViewHolder, position: Int) {
        val currentWeather = getItem(position)
        holder.bind(currentWeather)
    }

    class HourlyDataViewHolder private constructor(var binding: HourlyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): HourlyDataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourlyListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return HourlyDataViewHolder(binding)
            }
        }

        fun bind(currentWeather: CurrentWeather) {
            binding.weather = currentWeather
            binding.executePendingBindings()
        }
    }

}

class HourlyDiffCallback : DiffUtil.ItemCallback<CurrentWeather>() {
    override fun areItemsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
        return oldItem == newItem
    }
}