package com.eosr14.metaweathersearch.model

import com.google.gson.annotations.SerializedName

data class Location(
    val title: String = "",

    @SerializedName("consolidated_weather")
    val weatherList: List<ConsolidatedWeather> = listOf()
) {

    data class ConsolidatedWeather(
        @SerializedName("weather_state_name")
        val weatherStateName: String = "",

        @SerializedName("weather_state_abbr")
        val weatherStateAddr: String = "",

        @SerializedName("the_temp")
        val theTemp: Double = 0.0,

        @SerializedName("humidity")
        val humidity: Int = 0
    )

}