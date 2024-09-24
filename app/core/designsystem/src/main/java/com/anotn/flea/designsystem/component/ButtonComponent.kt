package com.anotn.flea.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.White
import com.anotn.flea.designsystem.theme.WriteIcon

@Composable
fun WriteButton(onWriteClick : () -> Unit) {
    FloatingActionButton(
        modifier = Modifier,
        onClick = onWriteClick,
        containerColor = Blue200,
        contentColor = White,
    ) {
        Column(
        ) {
            Icon(WriteIcon,
                contentDescription = "Write",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tint = White)
            Text(text = "등록",
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),)
        }
    }
}