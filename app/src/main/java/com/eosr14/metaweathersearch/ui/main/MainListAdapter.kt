package com.eosr14.metaweathersearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.R
import com.eosr14.metaweathersearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.metaweathersearch.common.base.BaseViewHolder
import com.eosr14.metaweathersearch.model.LocalWeather

class MainListAdapter : BaseRecyclerViewAdapter<LocalWeather, BaseViewHolder<LocalWeather>>() {

    override fun onBindView(holder: BaseViewHolder<LocalWeather>, position: Int) {
        if (holder is MainListViewHolder) {
            holder.bind(getItem(position - 1))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.item_weather_head
            else -> R.layout.item_weather
        }
    }

    override fun getItemCount(): Int {
        return when (getItems().isEmpty()) {
            true -> 0
            else -> super.getItemCount() + 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_weather_head -> MainListHeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_weather_head, parent, false
                )
            )

            else -> MainListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_weather, parent, false
                )
            )
        }
    }

}