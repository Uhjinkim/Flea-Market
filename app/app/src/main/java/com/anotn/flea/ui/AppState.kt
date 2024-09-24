package com.anotn.flea.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.anotn.flea.chat.navigation.navigateToChats
import com.anotn.flea.favorite.navigation.navigateToFavorite
import com.anotn.flea.feed.navigation.navigateToFeed
import com.anotn.flea.more.navigation.navigateToMore
import com.anotn.flea.navigation.TopLevelDestination
import com.anotn.flea.write.navigation.navigateToWrite
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) : AppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        AppState(
            navController = navController,
            coroutineScope = coroutineScope,
            )
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val currentDestination : NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination : TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull {
                currentDestination?.hasRoute(route = it.route) ?: false
            }
        }
    val shouldShowBottomBar : Boolean
        @Composable get() = currentTopLevelDestination != null

    val topLevelDestinations : List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
            trace("Navigation: ${destination.name}") {
                val topLevelNavOptions = navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                when (destination) {
                    TopLevelDestination.FEED -> navController.navigateToFeed(topLevelNavOptions)
                    TopLevelDestination.FAVORITE -> navController.navigateToFavorite(topLevelNavOptions)
                    TopLevelDestination.CHATS -> navController.navigateToChats(topLevelNavOptions)
                    TopLevelDestination.MORE -> navController.navigateToMore(topLevelNavOptions)
                }
            }
    }
    fun navigateToWrite() {

        navController.navigateToWrite()
    }

}