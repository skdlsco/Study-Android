package com.eka.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eka.mvvm.data.remote.DataRepository
import com.eka.mvvm.ui.base.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val useCase: MainUseCase,
                    private val dataRepository: DataRepository) : DisposableViewModel() {
    private val _temp = MutableLiveData<Double>()

    val temp: LiveData<Double> get() = _temp

    init {
        getTemp()
    }

    private fun getTemp() {
        dataRepository.getTemp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    _temp.value = it
                }
                .also { addDisposable(it) }
    }
}