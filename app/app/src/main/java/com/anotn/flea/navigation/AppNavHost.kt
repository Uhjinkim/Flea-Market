package com.anotn.flea.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.anotn.flea.chat.navigation.chatScreen
import com.anotn.flea.chat.navigation.chatsScreen
import com.anotn.flea.chat.navigation.navigateToChat
import com.anotn.flea.favorite.navigation.favoriteScreen
import com.anotn.flea.feed.navigation.FeedRoute
import com.anotn.flea.feed.navigation.feedScreen
import com.anotn.flea.feed.navigation.loginScreen
import com.anotn.flea.more.navigation.moreScreen
import com.anotn.flea.search.navigation.navigateToSearch
import com.anotn.flea.search.navigation.searchScreen
import com.anotn.flea.ui.AppState
import com.anotn.flea.write.navigation.writeScreen

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    ) {
    val navController = appState.navController
    fun onBottomNavigate(index: Int) {
        when (index) {
            0 -> appState.navigateToTopLevelDestination(destination = TopLevelDestination.FEED)
            1 -> appState.navigateToTopLevelDestination(destination = TopLevelDestination.FAVORITE)
            2 -> appState.navigateToTopLevelDestination(destination = TopLevelDestination.CHATS)
            3 -> appState.navigateToTopLevelDestination(destination = TopLevelDestination.MORE)
        }
    }
    NavHost(
        navController = navController,
        startDestination = FeedRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        feedScreen(
            onSearchClick = { navController.navigateToSearch() },

        )
        loginScreen(
            onLoginSuccess = { navController.navigate(FeedRoute) {
                popUpTo(FeedRoute) { inclusive = true }
            } },
            onLoginError = {}
        )
        favoriteScreen(
            onSearchClick = { navController.navigateToSearch() },
        )
        chatsScreen(
            onChatClick = { navController.navigateToChat() }
        )
        moreScreen(
        )
        writeScreen(
            onBackClick = { navController.popBackStack() },
            onAddClick = { navController.popBackStack() }
        )
        searchScreen(onSearchClick = {},
            onBackClick = { navController.popBackStack() }
        )
        chatScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}



