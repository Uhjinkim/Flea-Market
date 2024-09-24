package com.anotn.flea.data

import com.anotn.flea.data.datastore.TokenManager
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            tokenManager.getAccessToken()
        }
        synchronized(this) {
            val updatedToken = runBlocking {
                tokenManager.getAccessToken()
            }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking {  }
            }
        }
        return null
    }

}