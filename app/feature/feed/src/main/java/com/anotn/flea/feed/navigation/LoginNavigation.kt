package com.anotn.flea.feed.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anotn.flea.feed.LoginScreen
import kotlinx.serialization.Serializable

@Serializable data object LoginRoute

fun NavController.navigateToLogin() {
    navigate(LoginRoute) { popUpTo(FeedRoute) { inclusive = true} }
}

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
    onLoginError: () -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(
            context = LocalContext.current,
            onLoginSuccess = onLoginSuccess,
            onLoginError = onLoginError,
        )

    }

}