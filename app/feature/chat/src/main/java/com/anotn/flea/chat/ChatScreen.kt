package com.anotn.flea.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SubdirectoryArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anotn.flea.designsystem.component.ChatBar
import com.anotn.flea.designsystem.component.ChatBox
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.Gray200
import com.anotn.flea.designsystem.theme.Pink200
import com.anotn.flea.designsystem.theme.Pink500
import com.anotn.flea.designsystem.theme.Transperent
import com.anotn.flea.designsystem.theme.White

@Composable
fun ChatScreen(
    onBackClick: () -> Unit,
) {
    val chat = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { ChatBar(
            target = "거래자", user = "나",
            onBackClick = {
                keyboardController?.hide()
                onBackClick() }
        )},
        bottomBar = { ChatBox(chat = chat) }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier,
                state = rememberLazyListState()
            ) {
            }
        }
        BackHandler { onBackClick() }
    }
}

@Composable
fun ChatBubble() {

}
@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(onBackClick = {})
}