package com.eka.mvvm

import android.app.Application
import com.eka.mvvm.di.NetworkModules
import com.eka.mvvm.di.RepositoryModules
import com.eka.mvvm.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(NetworkModules.networkModules,
                    RepositoryModules.repositoryModules,
                    ViewModelModules.viewModelModules)
        }
    }
}