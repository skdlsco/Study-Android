package com.eka.databinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eka.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var item: Item = Item()
    lateinit var binding: ActivityMainBinding // lateinit 나중에 init한다는거임 ㅇㅅㅇ init안하고 쓰면 터짐

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) // bind

        item.text = "asdfadsf" // item

        binding.item = item //item 연결
        binding.notifyChange()  // notify
    }
}
