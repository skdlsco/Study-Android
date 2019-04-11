package com.eka.mvvm.di

import com.eka.mvvm.data.remote.NetworkAPI
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModules {

    const val baseUrl = "http://hangang.dkserver.wo.tc"

    val networkModules = module {
        single {
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
                    .create(NetworkAPI::class.java)
        }
    }
}