package com.eka.databinding

import android.databinding.BindingAdapter
import android.widget.TextView

object MyBindingAdapter {

    @JvmStatic
    @BindingAdapter("asdf")
    fun asdf(view: TextView, text: String) {
        view.text = "내용: $text"
    }

}