package com.anotn.flea.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anotn.flea.designsystem.component.BodyLargeText
import com.anotn.flea.designsystem.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchClick : () -> Unit,
    onBackClick: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchScreenState = remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                inputField = { SearchBarDefaults.InputField(
                    query = searchQuery,
                    onQueryChange = {searchQuery = it},
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = {},
                    placeholder = { BodyLargeText(modifier = Modifier, text = "검색어를 입력해주세요.") },
                    leadingIcon = { IconButton(
                        onClick = { keyboardController?.hide()
                            onBackClick() },
                        content = { Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "Back") }
                    ) },
                    trailingIcon = { IconButton(
                        onClick = onSearchClick,
                        content = { Icon(Icons.Outlined.Search, contentDescription = "Back") }
                    ) },
                )},
                expanded = true,
                onExpandedChange = {},
                colors = SearchBarDefaults.colors(
                    containerColor = White,

                )
            ) { Column {
                Spacer(modifier = Modifier.height(10.dp))
            } }
            BackHandler { keyboardController?.hide()
                onBackClick() }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onSearchClick = {},
        onBackClick = {},
    )
}