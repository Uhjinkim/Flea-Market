package com.anotn.flea.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class RetrofitClient {
    fun retrofitStart() : Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("localhost:8080/")
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json; charset=utf-8")!!))
            .build()
        return retrofit
    }
}