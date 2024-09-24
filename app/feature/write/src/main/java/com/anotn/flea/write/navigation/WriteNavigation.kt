package com.anotn.flea.write.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anotn.flea.write.WriteScreen
import kotlinx.serialization.Serializable

@Serializable data object WriteRoute

fun NavController.navigateToWrite() = navigate(WriteRoute)

fun NavGraphBuilder.writeScreen(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    composable<WriteRoute> {
        WriteScreen(
            onBackClick = onBackClick,
            onAddClick = onAddClick,
        )
    }
}