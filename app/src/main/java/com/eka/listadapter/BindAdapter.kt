package com.eka.listadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindAdapter {

    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun setItems(view: RecyclerView, items: ArrayList<Item>, vm: MainViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) this.submitList(items)
        } ?: run {
            ItemListAdapter(vm).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }
}