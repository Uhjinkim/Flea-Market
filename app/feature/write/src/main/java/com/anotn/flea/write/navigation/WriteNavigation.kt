package com.anotn.flea.write.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.write.WriteScreen
import kotlinx.serialization.Serializable

@Serializable data object WriteRoute

fun NavController.navigateToWrite() = navigate(WriteRoute)

fun NavGraphBuilder.writeScreen() {
    composable<WriteRoute> {
        WriteScreen()
    }
}