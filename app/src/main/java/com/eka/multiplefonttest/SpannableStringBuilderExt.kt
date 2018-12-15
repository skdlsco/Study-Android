package com.eka.multiplefonttest

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder

fun SpannableStringBuilder.append(text: String, font: Typeface): SpannableStringBuilder {
    append(text)
    setSpan(CustomTypefaceSpan(font), length - text.length, length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
    return append("")
}