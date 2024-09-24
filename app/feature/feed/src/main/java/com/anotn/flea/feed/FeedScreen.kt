package com.anotn.flea.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anotn.flea.designsystem.component.AppNavigationBar
import com.anotn.flea.designsystem.component.AppNavigationBarItem
import com.anotn.flea.designsystem.component.BodyLargeText
import com.anotn.flea.designsystem.component.FavoriteToggleButton
import com.anotn.flea.designsystem.component.LabelText
import com.anotn.flea.designsystem.component.TitleText
import com.anotn.flea.designsystem.component.WriteButton
import com.anotn.flea.designsystem.theme.Black
import com.anotn.flea.designsystem.theme.Blue200
import com.anotn.flea.designsystem.theme.ChatSelected
import com.anotn.flea.designsystem.theme.ChatUnselected
import com.anotn.flea.designsystem.theme.FavoriteSelected
import com.anotn.flea.designsystem.theme.FavoriteUnselected
import com.anotn.flea.designsystem.theme.FeedSelected
import com.anotn.flea.designsystem.theme.FeedUnselected
import com.anotn.flea.designsystem.theme.Gray200
import com.anotn.flea.designsystem.theme.MoreSelected
import com.anotn.flea.designsystem.theme.MoreUnselected
import com.anotn.flea.designsystem.theme.Red
import com.anotn.flea.designsystem.theme.White
import kotlin.math.ceil


/*@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
    onSearchClick : () -> Unit,

) {
    FeedScreen(
        modifier = modifier,
        onSearchClick = onSearchClick,
    )
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
    onSearchClick : () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleText(modifier = Modifier, title = "피드") },
                modifier = Modifier,
                actions = {
                    IconButton(
                        onClick = {
                            onSearchClick() },
                        content = { Icon(Icons.Filled.Search, contentDescription = "Search") }
                    )
                },
                expandedHeight = 56.dp,
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.topAppBarColors(),
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(WindowInsets(0, 0, 0, 0)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    TitleText(
                        title = "전체 카테고리",
                        modifier = Modifier
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow {
                items(10, itemContent = { index ->
                    Box {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .widthIn(min = 80.dp)
                                    .heightIn(min = 80.dp)
                                    .background(color = Gray200, shape = RoundedCornerShape(5.dp))
                            ) {
                                BodyLargeText(
                                    text = "카테고리${index + 1}",
                                    modifier = Modifier.align(Alignment.Center),
                                )
                            }
                        }
                    }
                })
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))
            val items = List(20) { "Item ${it + 1}" } // 예제 아이템
            val itemsPerPage = 6
            val pageCount = ceil(items.size / itemsPerPage.toDouble()).toInt()
            val pagerState = rememberPagerState(pageCount = { pageCount })
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                state = pagerState,
            ) { page ->
                val startIndex = page * itemsPerPage
                val endIndex = minOf(startIndex + itemsPerPage, items.size)
                val pageItems = items.subList(startIndex, endIndex)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    items(pageItems.size) { index ->
                        Column(
                            modifier = Modifier.padding(5.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 150.dp)
                                    .background(color = Gray200, shape = RoundedCornerShape(5.dp))
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                val isFavorite = rememberSaveable { mutableStateOf(false) }
                                FavoriteToggleButton(
                                    isFavorite = isFavorite,
                                    modifier = Modifier.align(Alignment.TopEnd),
                                )
                            }
                            LabelText(
                                label = pageItems[index],
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                            BodyLargeText(
                                text = "${(index * 1000 + 10000)}원",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
internal fun FeedScreenPreview() {
    FeedScreen(
        modifier = Modifier,
        onSearchClick = {},
    )
}