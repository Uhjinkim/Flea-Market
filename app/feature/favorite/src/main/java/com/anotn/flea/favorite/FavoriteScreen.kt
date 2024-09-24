package com.anotn.flea.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anotn.flea.designsystem.component.FavoriteToggleButton
import com.anotn.flea.designsystem.theme.Gray200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    onSearchClick: () -> Unit,
) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "관심") },
                actions = {
                    IconButton(
                        onClick = onSearchClick,
                        content = { Icon(Icons.Filled.Search, contentDescription = "Search") }
                    )
                },
                expandedHeight = 56.dp,
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.topAppBarColors(),
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
        ) {
            items(items) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 150.dp)
                        .padding(16.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .widthIn(200.dp)
                                .fillMaxHeight()
                                .background(color = Gray200, shape = RoundedCornerShape(8.dp))
                        ) {

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 10.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(top = 2.dp),
                                fontSize = 18.sp
                            )
                            Text(
                                text = "10,000원",
                                modifier = Modifier.padding(bottom = 2.dp),
                                fontSize = 18.sp
                            )
                        }
                    }
                    val isFavorite = rememberSaveable { mutableStateOf(false) }
                    FavoriteToggleButton(
                        isFavorite = isFavorite,
                        modifier = Modifier.align(
                            Alignment.TopEnd)
                            .then(Modifier.size(28.dp))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(
        onSearchClick = {}
    )
}