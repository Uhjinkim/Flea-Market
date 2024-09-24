package com.anotn.flea.designsystem.component

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon : ImageVector,
    selectedIcon : ImageVector,
    label : String,
    showIndicator : Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { if(selected)
            Icon(imageVector = selectedIcon, contentDescription = label)
        else Icon(imageVector = icon, contentDescription = label) },
        modifier = modifier,
        enabled = enabled,
        label = { Text(text = label, fontSize = 12.sp)},
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
            indicatorColor = if(showIndicator) AppNavigationDefaults.navigationIndicatorColor()
                    else NavigationBarDefaults.containerColor,
        ),
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {

    NavigationBar(
        modifier = modifier,
        contentColor = AppNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
        )
}
object AppNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}