package com.anotn.flea.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType

object RetrofitClient {
    private const val BASE_URL = "localhost:8080"
    private val contentType = MediaType.parse("application/json; charset=utf-8")!!
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

}