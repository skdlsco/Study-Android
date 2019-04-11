package com.eka.mvvm.di

import com.eka.mvvm.data.remote.DataRepository
import com.eka.mvvm.data.remote.DataRepositoryImpl
import org.koin.dsl.module

object RepositoryModules {

    val repositoryModules = module {
        factory {
            DataRepositoryImpl(get()) as DataRepository
        }
    }
}