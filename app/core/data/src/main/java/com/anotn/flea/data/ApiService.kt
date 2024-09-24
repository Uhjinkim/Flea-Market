package com.anotn.flea.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body userDTO: UserDTO): Response<Unit>

    @POST("reissue")
    suspend fun refreshToken(): Response<Unit>
}