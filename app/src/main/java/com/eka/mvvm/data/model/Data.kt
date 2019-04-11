package com.eka.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("result")
        val result: Boolean,
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("time")
        val time: String)