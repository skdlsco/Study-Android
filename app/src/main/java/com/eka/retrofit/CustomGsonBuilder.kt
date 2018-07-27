package com.eka.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory


object CustomGsonBuilder {
    fun getCustomConverter(): Converter.Factory {
        return GsonConverterFactory.create(getGsonBuilder())
    }


    // builder에 registerTypeAdapter 로 어떤 클래스가 왔을 때 이렇게 파싱 한다. 를 알려줌
    fun getGsonBuilder(): Gson {
        val builder = GsonBuilder()

        builder.registerTypeAdapter(Item::class.java, JsonDeserializer<Item> { json, _, _ ->
            var obj = json.asJsonObject
            Item(obj.opt("asdf"), obj.opt("asdf2"))// 요구한 클래스에 맞춰 반환
        })

        builder.registerTypeAdapter(object : TypeToken<ArrayList<Item>>() {}.type, JsonDeserializer<ArrayList<Item>> { json, _, _ ->
            var data = json.asJsonObject["data"].asJsonArray
            var wList = ArrayList<Item>().apply {
                addAll(data.map {
                    var obj = it.asJsonObject
                    Item(obj.opt("asdfasdf"), obj.opt("asdfasdf2"))
                })
            }
            wList
        })
        return builder.create()
    }

    private fun JsonObject.opt(key: String): String {
        return if (!this.has(key) || this[key].isJsonNull) {
            ""
        } else {
            this[key].asString
        }
    }
}