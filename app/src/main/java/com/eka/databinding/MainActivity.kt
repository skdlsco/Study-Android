package com.eka.databinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eka.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var item: Item = Item()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        item.text = "asdfadsf"

        binding.item = item
        binding.notifyChange()
    }
}
