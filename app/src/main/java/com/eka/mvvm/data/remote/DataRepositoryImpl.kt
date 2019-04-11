package com.eka.mvvm.data.remote

import io.reactivex.Single

class DataRepositoryImpl(private val api: NetworkAPI) : DataRepository {
    override fun getTemp(): Single<Double> {
        return api.getTemp().map { it.temp }
    }
}