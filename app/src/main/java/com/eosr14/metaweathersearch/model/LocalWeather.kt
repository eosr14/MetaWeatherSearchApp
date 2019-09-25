package com.eosr14.metaweathersearch.model

data class LocalWeather(
    var local: String = "",
    var todayWeatherStateName: String = "",
    var todayWeatherStatAbbr: String = "",
    var todayWeatherTheTemp: Int = 0,
    var todayWeatherHumidity: Int = 0,
    var tomorrowWeatherStateName: String = "",
    var tomorrowWeatherStateAbbr: String = "",
    var tomorrowWeatherTheTemp: Int = 0,
    var tomorrowWeatherHumidity: Int = 0
)