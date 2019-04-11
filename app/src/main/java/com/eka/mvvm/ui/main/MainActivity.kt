package com.eka.mvvm.ui.main

import android.os.Bundle
import com.eka.mvvm.R
import com.eka.mvvm.databinding.ActivityMainBinding
import com.eka.mvvm.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val useCase by lazy { MainUseCase(this) }
    private val viewModel: MainViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    // fragment 에서는 onCreateView 를 작성안해도됨 -> BaseFragment에서 처리
    // viewDataBinding.vm, lifeCycleOwner 를 onActivityCreated 등에서 처리 필요
    // ViewHolder 예제는 ListAdapter 참고
}