package com.eosr14.metaweathersearch.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected val progress = MutableLiveData<Boolean>(false)
    val isProgress: LiveData<Boolean> get() = progress

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }

}