package com.eosr14.metaweathersearch.network.services

import com.eosr14.metaweathersearch.model.Location
import com.eosr14.metaweathersearch.model.LocationSearch
import io.reactivex.Single
import retrofit2.http.*

interface MetaWeatherService {

    @GET("/api/location/search")
    fun requestLocationSearch(@Query("query") query : String) : Single<LocationSearch>

    @GET("/api/location")
    fun requestLocation(@Path("woeid") woeId : Int) : Single<Location>

}