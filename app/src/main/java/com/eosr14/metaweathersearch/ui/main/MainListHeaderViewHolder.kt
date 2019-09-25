package com.eosr14.metaweathersearch.ui.main

import com.eosr14.metaweathersearch.common.base.BaseViewHolder
import com.eosr14.metaweathersearch.databinding.ItemWeatherHeadBinding
import com.eosr14.metaweathersearch.model.LocalWeather

class MainListHeaderViewHolder(private val binding: ItemWeatherHeadBinding) :
    BaseViewHolder<LocalWeather>(binding.root) {
    override fun bind(item: LocalWeather) {
        binding.localWeather = item
    }
}