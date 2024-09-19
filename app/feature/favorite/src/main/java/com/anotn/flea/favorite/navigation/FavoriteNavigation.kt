package com.anotn.flea.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.favorite.FavoriteScreen
import kotlinx.serialization.Serializable

@Serializable data object FavoriteRoute

fun NavController.navigateToFavorite(navOptions: NavOptions) = navigate(FavoriteRoute, navOptions)

fun NavGraphBuilder.favoriteScreen() {
    composable<FavoriteRoute> {
        FavoriteScreen()
    }
}