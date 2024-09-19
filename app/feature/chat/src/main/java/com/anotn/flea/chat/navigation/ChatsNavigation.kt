package com.anotn.flea.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.chat.ChatsScreen
import kotlinx.serialization.Serializable

@Serializable data object ChatsRoute

fun NavController.navigateToChats(navOptions: NavOptions) = navigate(ChatsRoute, navOptions)

fun NavGraphBuilder.chatsScreen() {
    composable<ChatsRoute> {
        ChatsScreen()
    }
}