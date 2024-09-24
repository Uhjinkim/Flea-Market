package com.anotn.flea.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Gray200
import com.anotn.flea.designsystem.theme.LightBlue200
import com.anotn.flea.designsystem.theme.White

@Composable
fun ChatsScreen(
    onChatClick : () -> Unit
) {
    Scaffold(
    ) { padding ->
        Box(modifier = Modifier.padding(padding)
            .background(color = White)
            .fillMaxSize()) {

            LazyColumn(
                modifier = Modifier,
            ) {
                item {
                    ChatRoom(roomId = "1", recentMessage = "Hello", onChatClick = onChatClick)
                    ChatRoom(roomId = "2", recentMessage = "Hi", onChatClick = onChatClick)
                    ChatRoom(roomId = "3", recentMessage = "Hey", onChatClick = onChatClick)
                    ChatRoom(roomId = "4", recentMessage = "Hola", onChatClick = onChatClick)
                    ChatRoom(roomId = "5", recentMessage = "Bonjour", onChatClick = onChatClick)
                }
            }
        }
    }
}
@Composable
fun ChatRoom(
    roomId: String,
    recentMessage: String,
    onChatClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 5.dp)) {
        Box(modifier = Modifier
            .clickable(onClick = onChatClick)
            .fillMaxSize()
            .align(Alignment.CenterVertically)
            .heightIn(56.dp)
            .drawBehind {
                val y = size.height - 1.dp.toPx()
                drawLine(
                    color = Black,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth = 1.dp.toPx(),
                )
            }
        ) {
            Text(roomId,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dp))
            Spacer(modifier = Modifier.widthIn(10.dp))
            Text(recentMessage,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp))
        }

    }
}
@Preview
@Composable
fun ChatsScreenPreview() {
    ChatsScreen(
        onChatClick = {}
    )
}