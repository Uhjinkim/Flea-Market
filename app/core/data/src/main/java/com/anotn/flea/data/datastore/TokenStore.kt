package com.anotn.flea.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TokenStore @Inject constructor(
    private val dataStore: DataStore<Preferences>) : TokenManager {
        companion object {
            val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
            val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        }
    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
        }
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        val edit = dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }.first()
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }.first()
    }

    override suspend fun clearAllTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}