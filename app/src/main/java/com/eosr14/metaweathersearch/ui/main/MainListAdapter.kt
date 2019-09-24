package com.eosr14.metaweathersearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.R
import com.eosr14.metaweathersearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.metaweathersearch.model.LocalWeather

class MainListAdapter(onItemClickListener: OnItemClickListener) : BaseRecyclerViewAdapter<LocalWeather, MainListViewHolder>() {

    init {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindView(holder: MainListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.item_weather_head
            else -> R.layout.item_weather
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_weather, parent, false))
    }
}