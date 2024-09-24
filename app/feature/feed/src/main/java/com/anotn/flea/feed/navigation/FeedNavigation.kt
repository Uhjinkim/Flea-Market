package com.anotn.flea.feed.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.feed.FeedScreen
import kotlinx.serialization.Serializable

@Serializable
data object FeedRoute

fun NavController.navigateToFeed(navOptions: NavOptions) = navigate(FeedRoute, navOptions)

fun NavGraphBuilder.feedScreen(
    onSearchClick : () -> Unit, ){
    composable<FeedRoute>(
        enterTransition = {null},
        exitTransition = {null},
    ) {
        FeedScreen(
            modifier = Modifier,
            onSearchClick = onSearchClick,
        )
    }
}