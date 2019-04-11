package com.eka.mvvm.data.remote

import io.reactivex.Single

interface DataRepository {
    fun getTemp(): Single<Double>
}