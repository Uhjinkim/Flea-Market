package com.anotn.flea.search.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anotn.flea.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable data object SearchRoute

fun NavController.navigateToSearch() = navigate(SearchRoute)

fun NavGraphBuilder.searchScreen(
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable<SearchRoute>(
        enterTransition = { slideInHorizontally { it } },
        popEnterTransition = { null },
        exitTransition = { null },
    ) {
        SearchScreen(
            onSearchClick = onSearchClick,
            onBackClick = onBackClick,
        )
    }
}