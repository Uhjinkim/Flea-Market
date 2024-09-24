package com.anotn.flea.chat.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.chat.ChatScreen
import com.anotn.flea.chat.ChatsScreen
import kotlinx.serialization.Serializable

@Serializable data object ChatsRoute

@Serializable data object ChatRoute

fun NavController.navigateToChats(navOptions: NavOptions) = navigate(ChatsRoute, navOptions)

fun NavController.navigateToChat() = navigate(ChatRoute)
fun NavGraphBuilder.chatsScreen(
    onChatClick : () -> Unit
) {
    composable<ChatsRoute>
     {
        ChatsScreen(
            onChatClick = onChatClick
        )
    }
}

fun NavGraphBuilder.chatScreen(
    onBackClick : () -> Unit,
) {
    composable<ChatRoute>(
        enterTransition = { slideInVertically{ it } },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { slideOutVertically { -it / 3 } + fadeOut() }
    ) {
        ChatScreen(
            onBackClick = onBackClick
        )
    }
}