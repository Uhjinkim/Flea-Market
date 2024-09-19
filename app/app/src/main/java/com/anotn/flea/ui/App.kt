package com.anotn.flea.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anotn.flea.designsystem.component.AppNavigationBar
import com.anotn.flea.designsystem.component.AppNavigationBarItem
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.White
import com.anotn.flea.navigation.AppNavHost
import com.anotn.flea.navigation.TopLevelDestination
import com.anotn.flea.write.navigation.navigateToWrite

@Composable
fun App(
    appState: AppState,
    modifier: Modifier = Modifier,
) {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
    appState: AppState,
    modifier: Modifier = Modifier,
    onTopBarActionClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                ),
            ) {
                val destination = appState.currentTopLevelDestination
                var showTopBar = false
                if(destination != TopLevelDestination.WRITE) {
                    showTopBar =true
                    TopAppBar(
                        title = { Text(text = "Flea Market") },
                        modifier = Modifier,
                        actions = {
                            IconButton(
                                onClick = { },
                                content = { Icon(Icons.Filled.Search, contentDescription = "Search") }
                            )
                        },
                        expandedHeight = 56.dp,
                        windowInsets = TopAppBarDefaults.windowInsets,
                        colors = TopAppBarDefaults.topAppBarColors(),
                    )
                }
                Box(
                    modifier = Modifier.consumeWindowInsets(
                        if(showTopBar) WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                        else WindowInsets(0, 0, 0, 0)
                    ),
                ) {
                    AppNavHost(
                        appState = appState,)
                }

            }
        },
        bottomBar = {
            if(
                appState.currentTopLevelDestination?.route != TopLevelDestination.WRITE.route) {
                AppNavigationBar {
                    TopLevelDestination.entries.forEachIndexed { index, destination ->
                        val selected =
                            appState.currentTopLevelDestination?.route == destination.route
                        if (index == 2) {
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                FloatingActionButton(
                                    onClick = { appState.navController.navigateToWrite() },
                                    containerColor = Blue200,
                                    contentColor = White,
                                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                                ) {
                                    Column {
                                        Icon(Icons.Filled.Add, contentDescription = "Add")
                                        Text(
                                            text = "등록",
                                            modifier = Modifier.align(Alignment.CenterHorizontally),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        } else {
                            AppNavigationBarItem(
                                selected = selected,
                                onClick = { appState.navigateToTopLevelDestination(destination) },
                                modifier = modifier,
                                enabled = true,
                                alwaysShowLabel = true,
                                icon = destination.unselectedIcon,
                                selectedIcon = destination.selectedIcon,
                                label = destination.titleTextId,
                                showIndicator = false,
                            )
                        }

                    }
                }
            }
        }
    )
}
@Composable
@Preview
fun AppPreview() {
    App(
        appState = rememberAppState(),
        modifier = Modifier,
        onTopBarActionClick = {},
    )
}