package com.eosr14.metaweathersearch.model

data class LocalWeather(
    var local: String = "",
    var todayWeatherStateName: String = "",
    var todayWeatherStatAbbr: String = "",
    var todayWeatherTheTemp: String = "",
    var todayWeatherHumidity: String = "",
    var tomorrowWeatherStateName: String = "",
    var tomorrowWeatherStateAbbr: String = "",
    var tomorrowWeatherTheTemp: String = "",
    var tomorrowWeatherHumidity: String = ""
)