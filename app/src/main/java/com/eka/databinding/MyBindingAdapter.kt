package com.eka.databinding

import android.databinding.BindingAdapter
import android.widget.TextView


// object -> 전부 다 Companion
object MyBindingAdapter {

    @JvmStatic // 필수
    @BindingAdapter("asdf") // attr에서 쓰일 이름 넣기
    fun asdf(view: TextView, text: String) { // 함수 이름 무관, view 뒤에는 받아올 인자 값 다중은 잘 기억이 안난다.
        view.text = "내용: $text"
    }

}