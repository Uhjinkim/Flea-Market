package com.anotn.flea.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun TopBar(
    topBarBackgroundType: TopBarBackgroundType,
    title: String = "",
    leftIconPainter: Painter,
    leftIconContentDescription: String,
    onLeftIconButtonClick: () -> Unit,

    ) {

}
@Composable
private fun TextTitle(
    title: String,
    contentColor: State<Color>,
    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier,
        ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f, fill = false)

        )
    }
}

enum class TopBarBackgroundType {
    WHITE, TRANSPARENT
}