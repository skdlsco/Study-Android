package com.eka.listadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val toastMaker: ToastMaker) : ViewModel() {

    val _items = MutableLiveData<ArrayList<Item>>()

    val items: LiveData<ArrayList<Item>> get() = _items

    init {
        _items.value = ArrayList<Item>().apply {
            (1..20).forEach {
                add(Item("title$it", "content$it"))
            }
        }
    }

    fun onItemClick(item: Item, pos: Int) {
        toastMaker.makeToast(item.content)
    }
}