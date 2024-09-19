package com.anotn.flea.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.anotn.flea.chat.navigation.chatsScreen
import com.anotn.flea.favorite.navigation.favoriteScreen
import com.anotn.flea.feed.navigation.FeedRoute
import com.anotn.flea.feed.navigation.feedScreen
import com.anotn.flea.more.navigation.moreScreen
import com.anotn.flea.ui.AppState
import com.anotn.flea.ui.rememberAppState
import com.anotn.flea.write.navigation.writeScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    ) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = FeedRoute,
        modifier = modifier
    ) {
        feedScreen()
        favoriteScreen()
        chatsScreen()
        moreScreen()
        writeScreen()
    }
}

