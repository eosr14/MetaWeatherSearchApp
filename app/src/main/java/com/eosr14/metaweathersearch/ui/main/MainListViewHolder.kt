package com.eosr14.metaweathersearch.ui.main

import com.eosr14.metaweathersearch.common.base.BaseViewHolder
import com.eosr14.metaweathersearch.databinding.ItemWeatherBinding
import com.eosr14.metaweathersearch.model.LocalWeather

class MainListViewHolder(private val binding: ItemWeatherBinding) :
    BaseViewHolder<LocalWeather>(binding.root) {
    override fun bind(item: LocalWeather) {
        binding.localWeather = item
    }
}