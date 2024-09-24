package com.anotn.flea.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.FavoriteSelected
import com.anotn.flea.designsystem.theme.FavoriteUnselected
import com.anotn.flea.designsystem.theme.Red

@Composable
fun FavoriteToggleButton(
    isFavorite: MutableState<Boolean>, modifier: Modifier) {
    IconToggleButton(
        modifier = modifier,
        checked = isFavorite.value,
        onCheckedChange = { isFavorite.value = it }
    ) {
        if (isFavorite.value) {
            Icon(
                imageVector = FavoriteSelected,
                contentDescription = "Favorite",
                tint = Red
            )
        } else {
            Icon(
                imageVector = FavoriteUnselected,
                contentDescription = "Favorite",
                tint = Black
            )
        }
    }
}