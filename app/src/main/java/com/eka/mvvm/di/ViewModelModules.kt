package com.eka.mvvm.di

import com.eka.mvvm.ui.main.MainUseCase
import com.eka.mvvm.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModules {

    val viewModelModules = module {
        viewModel { (useCase: MainUseCase) ->
            MainViewModel(useCase, get())
        }
    }
}