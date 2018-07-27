package com.eka.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {
    private const val url = "http://10.0.2.2:3000"

    private var retrofit: Retrofit? = null
    val retrofitInstance: NetworkAPI
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!.create<NetworkAPI>(NetworkAPI::class.java)
        }
}