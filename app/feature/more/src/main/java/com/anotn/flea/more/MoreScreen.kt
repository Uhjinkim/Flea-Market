package com.anotn.flea.more

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Gray200
import com.anotn.flea.designsystem.theme.Pink200
import com.anotn.flea.designsystem.theme.White

@Composable
fun MoreScreen(

) {
    val profile = rememberSaveable { mutableStateOf("내 이름") }
    Scaffold(
    ) { padding ->
        Column(modifier = Modifier.padding(padding)
            .fillMaxSize(),) {
            ProfileContainer(profile = profile)

        }
    }
}

@Composable
fun ProfileContainer(profile: MutableState<String>) {
    var isEditing by remember { mutableStateOf(false) }
    val edit = remember { mutableStateOf(profile.value) } .also {
        if(isEditing) profile.value = it.value
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(color = Gray200),) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = Pink200,
                contentColor = White,
                disabledContainerColor = Pink200,
                disabledContentColor = White,
            ),
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(16.dp)) {
            Text(text = "로그아웃")
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .absoluteOffset(y = 50.dp)
            .width(100.dp)
            .height(100.dp)
            .border(1.dp, color = Black, shape = RoundedCornerShape(percent = 50))
            .background(color = White, shape = RoundedCornerShape(percent = 50)),
            )

    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .drawBehind {
            val y = size.height
            drawLine(
                color = Black,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            ) },
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.width(24.dp))
            AnimatedContent(
                targetState = isEditing, label = "") { show ->
                if(show)
                    TextField(
                        value = edit.value,
                        onValueChange = { edit.value = it },
                        modifier = Modifier.align(Alignment.CenterVertically),
                        singleLine = true,
                    )
                else {
                    Text(
                        text = profile.value,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }

            }
            IconToggleButton(
                checked = isEditing,
                modifier = Modifier.size(24.dp),
                onCheckedChange = { isEditing = it },
                content = { Icon(Icons.Outlined.Edit, contentDescription = "Edit") },
            )
        }
    }
}

@Preview
@Composable
fun MoreScreenPreview() {
    MoreScreen()
}