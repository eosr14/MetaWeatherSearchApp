package com.eosr14.metaweathersearch.ui.main

import com.eosr14.metaweathersearch.common.SEARCH_TEXT
import com.eosr14.metaweathersearch.common.base.BaseViewModel
import com.eosr14.metaweathersearch.network.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val mainViewModelInterface: MainViewModelInterface
) : BaseViewModel() {

    init {
        requestLocationSearch()
    }

    fun requestLocationSearch() {
        addDisposable(
            RetrofitManager.requestLocationSearch(SEARCH_TEXT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ locationSearch ->

                }, {

                })
        )
    }


}