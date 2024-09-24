package com.anotn.flea.data.repository

interface UserDataRepository {
    suspend fun getUserData(): String
}