package com.anotn.flea.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.anotn.flea.chat.navigation.ChatsRoute
import com.anotn.flea.favorite.navigation.FavoriteRoute
import com.anotn.flea.feed.navigation.FeedRoute
import com.anotn.flea.more.navigation.MoreRoute
import com.anotn.flea.write.navigation.WriteRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: String,
    val titleTextId: String,
    val route: KClass<*>,
) {
    FEED(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = "feed",
        titleTextId = "피드",
        route = FeedRoute::class
    ),
    FAVORITE(
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        iconTextId = "favorite",
        titleTextId = "관심",
        route = FavoriteRoute::class
    ),

    CHATS(
        selectedIcon = Icons.Filled.ChatBubble,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = "chats",
        titleTextId = "채팅",
        route = ChatsRoute::class
    ),
    MORE(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = "more",
        titleTextId = "MY",
        route = MoreRoute::class
    ),
}