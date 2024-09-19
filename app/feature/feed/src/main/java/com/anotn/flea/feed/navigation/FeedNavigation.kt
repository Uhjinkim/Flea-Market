package com.anotn.flea.feed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.feed.FeedScreen
import kotlinx.serialization.Serializable
import java.lang.reflect.Modifier

@Serializable
data object FeedRoute

fun NavController.navigateToFeed(navOptions: NavOptions) = navigate(FeedRoute, navOptions)


fun NavGraphBuilder.feedScreen() {
    composable<FeedRoute> {
        FeedScreen()
    }
}