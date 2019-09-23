package com.eosr14.metaweathersearch.model

import com.google.gson.annotations.SerializedName

data class LocationSearch(
    @SerializedName("title")
    val title: String = "",

    @SerializedName("location_type")
    val locationType: String = "",

    @SerializedName("woeid")
    val woeId: Int = 0,

    @SerializedName("latt_long")
    val lattLong: Double = 0.0
)