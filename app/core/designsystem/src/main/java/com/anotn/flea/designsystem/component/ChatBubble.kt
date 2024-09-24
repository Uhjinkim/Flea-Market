package com.anotn.flea.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SubdirectoryArrowRight
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.Pink200
import com.anotn.flea.designsystem.theme.Pink500
import com.anotn.flea.designsystem.theme.Transperent
import com.anotn.flea.designsystem.theme.White

@Composable
fun MyChatBubble(chat: String) {
    Box(
        modifier = Modifier.heightIn(min = 24.dp)
            .background(color = Blue200, shape = RoundedCornerShape(
                topStart = 8.dp, topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 2.dp))
    ) {
        Text(text = chat,
            modifier = Modifier.align(Alignment.Center))
    }
}
@Composable
fun MyChatBubbleFloating(chat: String) {
    Box(
        modifier = Modifier.heightIn(min = 24.dp)
            .background(color = Blue200, shape = RoundedCornerShape(
                topStart = 8.dp, topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
    ) {
        Text(text = chat,
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun YourChatBubble(chat: String) {
    Box(
        modifier = Modifier.heightIn(min = 24.dp)
            .background(color = Pink500, shape = RoundedCornerShape(
                topStart = 8.dp, topEnd = 8.dp, bottomStart = 2.dp, bottomEnd = 8.dp))
    ) {
        Text(text = chat,
            color = White,
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun YourChatBubbleFloating(chat: String) {
    Box(
        modifier = Modifier.heightIn(min = 24.dp)
            .background(color = Pink500, shape = RoundedCornerShape(
                topStart = 8.dp, topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
    ) {
        Text(text = chat,
            color = White,
            modifier = Modifier.align(Alignment.Center))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBar(target: String, user: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = target) },
        navigationIcon = { IconButton(
            onClick = onBackClick,
        )  { Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "Back") } },
    )
}

@Composable
fun ChatBox(chat: MutableState<String>) {
    BottomAppBar(
        modifier = Modifier,
        containerColor = Blue200,
    ) {
        TextField(
            value = chat.value,
            onValueChange = { chat.value = it },
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                unfocusedIndicatorColor = Transperent,
                focusedIndicatorColor = Transperent,
                disabledIndicatorColor = Transperent,

                )
        )
        Box(
            modifier = Modifier
                .padding(10.dp)
                .widthIn(56.dp)
                .heightIn(56.dp),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(48.dp, 56.dp)
                    .heightIn(48.dp, 56.dp),
                contentColor = Black,
                containerColor = Pink200,
                onClick = {},
                content = {
                    Icon(
                        Icons.Filled.SubdirectoryArrowRight,
                        contentDescription = "Search"
                    )
                }
            )
        }
    }
}