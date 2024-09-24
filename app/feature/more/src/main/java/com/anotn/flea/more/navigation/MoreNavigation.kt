package com.anotn.flea.more.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.more.MoreScreen

import kotlinx.serialization.Serializable

@Serializable data object MoreRoute

fun NavController.navigateToMore(navOptions: NavOptions) = navigate(MoreRoute, navOptions)

fun NavGraphBuilder.moreScreen(
) {
    composable<MoreRoute> {
        MoreScreen()
    }
}