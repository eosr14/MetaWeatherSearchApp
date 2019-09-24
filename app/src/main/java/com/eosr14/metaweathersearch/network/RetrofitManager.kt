package com.eosr14.metaweathersearch.network

import com.eosr14.metaweathersearch.common.META_WEATHER_BASE_URL
import com.eosr14.metaweathersearch.model.Location
import com.eosr14.metaweathersearch.model.LocationSearch
import com.eosr14.metaweathersearch.network.services.MetaWeatherService
import io.reactivex.Single


object RetrofitManager {

    private fun provideMetaWeather(): MetaWeatherService {
        return RetrofitClient().provideRetrofit(META_WEATHER_BASE_URL)
            .create(MetaWeatherService::class.java)
    }

    fun requestLocationSearch(query: String): Single<List<LocationSearch>> {
        return provideMetaWeather().requestLocationSearch(query)
    }

    fun requestLocation(woeId: Int): Single<Location> {
        return provideMetaWeather().requestLocation(woeId)
    }

}