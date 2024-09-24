package com.anotn.flea.data

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
)