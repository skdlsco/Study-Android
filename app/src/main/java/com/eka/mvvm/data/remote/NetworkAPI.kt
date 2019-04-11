package com.eka.mvvm.data.remote

import com.eka.mvvm.data.model.Data
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkAPI {
    @GET("/")
    fun getTemp(): Single<Data>
}