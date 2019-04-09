package com.eka.listadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.eka.listadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val toastMaker by lazy { ToastMaker(this) }
    private val viewModelFactory by lazy { MainViewModelFactory(toastMaker) }
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }

    lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.vm = viewModel
    }
}
