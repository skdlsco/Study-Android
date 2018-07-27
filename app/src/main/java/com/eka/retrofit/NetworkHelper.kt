package com.eka.retrofit

import retrofit2.Retrofit

object NetworkHelper {
    private const val url = "http://10.0.2.2:3000"

    private var retrofit: Retrofit? = null
    val retrofitInstance: NetworkAPI
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(CustomGsonBuilder.getCustomConverter())
                        .build()
            }
            return retrofit!!.create<NetworkAPI>(NetworkAPI::class.java)
        }
}