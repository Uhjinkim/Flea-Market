package com.anotn.flea.write

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun WriteScreen(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    WriteScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onAddClick = onAddClick,
        title = remember { mutableStateOf("") },
        category = remember { mutableStateOf("") },
        content = remember { mutableStateOf("") },

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    title: MutableState<String>,
    category: MutableState<String>,
    content: MutableState<String>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text(text = "거래 등록") },
            modifier = Modifier,
            navigationIcon = {
                IconButton(
                    onClick = { keyboardController?.hide()
                        onBackClick() },
                    content = {
                        Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Back") }
                ) },
            actions = {
                IconButton(
                    onClick = { /*TODO: 거래글 등록 후 글 화면으로 이동*/},
                    content = {
                        Icon(Icons.Filled.Add, contentDescription = "Add") }
                ) },
            expandedHeight = 56.dp,
            windowInsets = TopAppBarDefaults.windowInsets,
            colors = TopAppBarDefaults.topAppBarColors(),
            ) },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("상품명") }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = category.value,
                    onValueChange = { category.value = it },
                    label = { Text("카테고리") }
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .heightIn(min = 200.dp),
                    value = content.value,
                    onValueChange = { content.value = it },
                    label = { Text("설명") }
                )
            }
            BackHandler { keyboardController?.hide()
                onBackClick() }
        }

}
@Preview
@Composable
fun PreviewWriteScreen() {
    WriteScreen(
        onBackClick = {},
        onAddClick = {},
    )
}