package com.anotn.flea.ui

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.anotn.flea.chat.navigation.ChatRoute
import com.anotn.flea.designsystem.component.AppNavigationBar
import com.anotn.flea.designsystem.component.AppNavigationBarItem
import com.anotn.flea.designsystem.component.BodyLargeText
import com.anotn.flea.designsystem.component.LabelText
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.Transperent
import com.anotn.flea.designsystem.theme.White
import com.anotn.flea.feed.navigation.FeedRoute
import com.anotn.flea.navigation.AppNavHost
import com.anotn.flea.navigation.TopLevelDestination
import com.anotn.flea.write.navigation.navigateToWrite
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.reflect.KClass

@Composable
fun App(
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    App(
        appState = appState,
        snackbarHostState = snackbarHostState,
        onTopBarActionClick = {},
    )
}
@Composable
internal fun App(
    appState: AppState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onTopBarActionClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBottomBar,
                enter = slideInVertically { it },
                exit = if(appState.currentDestination?.hasRoute(ChatRoute::class) == true) {
                    ExitTransition.None } else slideOutVertically { it },

                ) { AppNavigationBar(modifier = Modifier.height(72.dp)) {
                    TopLevelDestination.entries.forEachIndexed { index, destination ->
                        val selected =
                        (appState.currentTopLevelDestination?.route == destination.route)
                        AppNavigationBarItem(
                            selected = selected,
                            onClick = { appState.navigateToTopLevelDestination(destination) },
                            modifier = modifier.weight(1f),
                            enabled = true,
                            alwaysShowLabel = true,
                            icon = destination.unselectedIcon,
                            selectedIcon = destination.selectedIcon,
                            label = destination.titleTextId,
                            showIndicator = false,
                        )
                        if (index == 1) {
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                FloatingActionButton(
                                    modifier = Modifier.widthIn(56.dp)
                                        .heightIn(56.dp)
                                        .border(2.dp, Black, RoundedCornerShape(14.dp)),
                                    onClick = { appState.navigateToWrite() },
                                    containerColor = White,
                                    shape = RoundedCornerShape(14.dp),
                                    contentColor = Black,
                                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                                ) {
                                    Column {
                                        Icon(
                                            Icons.Filled.Add,
                                            contentDescription = "Add",
                                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                ),
            ) {
                AppNavHost(
                    modifier = Modifier,
                    appState = appState,)
            }
            val context = LocalContext.current
            var backPressedTime = 0L
            val coroutineScope = rememberCoroutineScope()
            BackHandler {
                if (System.currentTimeMillis() - backPressedTime <= 1000L) {
                    (context as Activity).finish()
                } else {
                    backPressedTime = System.currentTimeMillis()
                    coroutineScope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            "뒤로 가기를 한 번 더 누르면 종료됩니다.",
                            duration = SnackbarDuration.Short)
                    }
                }
            }
        },
    )
}
@Composable
@Preview
fun AppPreview() {
    App(
        appState = rememberAppState(),
        modifier = Modifier,
    )
}