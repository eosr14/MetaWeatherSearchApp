package com.eosr14.metaweathersearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eosr14.metaweathersearch.common.META_WEATHER_IMG_URL
import com.eosr14.metaweathersearch.common.SEARCH_TEXT
import com.eosr14.metaweathersearch.common.base.BaseViewModel
import com.eosr14.metaweathersearch.model.LocalWeather
import com.eosr14.metaweathersearch.model.Location
import com.eosr14.metaweathersearch.model.LocationSearch
import com.eosr14.metaweathersearch.network.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val mainViewModelInterface: MainViewModelInterface
) : BaseViewModel() {

    private val _localWeathers = MutableLiveData<List<LocalWeather>>(mutableListOf())
    val localWeathers: LiveData<List<LocalWeather>> get() = _localWeathers

    private val _isFullToRefresh = MutableLiveData<Boolean>(false)
    val isFullToRefresh: LiveData<Boolean> get() = _isFullToRefresh

    init {
        requestLocationSearch(false)
    }

    fun requestLocationSearch(fullToRefresh: Boolean) {
        when (fullToRefresh) {
            true -> {
                progress.value = false
                _isFullToRefresh.value = true
            }
            else -> {
                progress.value = true
                _isFullToRefresh.value = false
            }
        }

        _localWeathers.value = mutableListOf()

        addDisposable(
            RetrofitManager.requestLocationSearch(SEARCH_TEXT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ locationSearch ->
                    requestLocation(locationSearch)
                }, {
                    progress.value = false
                    _isFullToRefresh.value = false
                    mainViewModelInterface.showErrorToast()
                })
        )
    }

    private fun requestLocation(list: List<LocationSearch>) {
        val localWeatherList = mutableListOf<LocalWeather>()
        val locations = mutableListOf<Single<Location>>()
        list.map { locationSearch -> locations.add(RetrofitManager.requestLocation(locationSearch.woeId)) }


        addDisposable(
            Single.concat(locations)
                .filter { location -> location.weatherList.size >= 2 }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ location ->
                    val localWeather = LocalWeather(local = location.title)
                    for (i in 0 until 2) {
                        when (i) {
                            0 -> {
                                // Today
                                localWeather.todayWeatherStateName =
                                    location.weatherList[i].weatherStateName
                                localWeather.todayWeatherStatAbbr = String.format(
                                    META_WEATHER_IMG_URL,
                                    location.weatherList[i].weatherStateAddr
                                )
                                localWeather.todayWeatherTheTemp =
                                    location.weatherList[i].theTemp.toInt()
                                localWeather.todayWeatherHumidity = location.weatherList[i].humidity
                            }

                            1 -> {
                                // Tomorrow
                                localWeather.tomorrowWeatherStateName =
                                    location.weatherList[i].weatherStateName
                                localWeather.tomorrowWeatherStateAbbr = String.format(
                                    META_WEATHER_IMG_URL,
                                    location.weatherList[i].weatherStateAddr
                                )
                                localWeather.tomorrowWeatherTheTemp =
                                    location.weatherList[i].theTemp.toInt()
                                localWeather.tomorrowWeatherHumidity =
                                    location.weatherList[i].humidity
                            }
                        }
                    }

                    localWeatherList.add(localWeather)
                    if (locations.size == localWeatherList.size) {
                        _localWeathers.value = localWeatherList
                        progress.value = false
                        _isFullToRefresh.value = false
                    }

                }, {
                    progress.value = false
                    _isFullToRefresh.value = false
                    mainViewModelInterface.showErrorToast()
                })
        )
    }

}