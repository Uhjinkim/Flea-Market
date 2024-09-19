package com.anotn.flea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anotn.flea.designsystem.theme.FleaTheme
import com.anotn.flea.ui.App
import com.anotn.flea.ui.rememberAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleaTheme {
                App(
                    appState = rememberAppState(),
                    modifier = Modifier,
                    onTopBarActionClick = {},
                )
                }
            }
        }
    }

@Preview
@Composable
fun FeedScreenPreview() {
    App(appState = rememberAppState(),
        modifier = Modifier,
        onTopBarActionClick = {})
}