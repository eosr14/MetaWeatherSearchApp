package com.eosr14.metaweathersearch.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.model.LocalWeather
import com.eosr14.metaweathersearch.ui.main.MainListAdapter

object DataBindingComponents {

    @JvmStatic
    @BindingAdapter("localWeatherItems")
    fun setLocalWeatherItems(recyclerView: RecyclerView, items: MutableList<LocalWeather>) {
        recyclerView.adapter?.let {
            if (it is MainListAdapter) {
                if (items == null) it.clearItems() else it.setItems(items)
                recyclerView.scheduleLayoutAnimation()
            }
        }
    }

}