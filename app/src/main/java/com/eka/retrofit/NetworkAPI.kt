package com.eka.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkAPI {


    @Multipart
    @POST("/file")
    fun postFile(@Part part: MultipartBody.Part,
                 @Part("asdf") asdf: RequestBody): Call<ResponseBody>
}