package com.anotn.flea.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anotn.flea.designsystem.theme.Blue200

@Composable
internal fun FeedScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(
                    text = "전체 카테고리",
                    fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(10, itemContent = { index ->
                Box {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Item ${index+1}",
                            modifier = Modifier.align(Alignment.Start),
                            fontSize = 20.sp)
                        Box(
                            modifier = Modifier
                                .widthIn(min = 150.dp)
                                .heightIn(min = 150.dp)
                                .background(color = Blue200)
                        )
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
            content = {
                Text(
                    text = "최근 본 상품",
                    fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(10, itemContent = { index ->
                Box {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Item ${index+1}",
                            modifier = Modifier.align(Alignment.Start),
                            fontSize = 20.sp)
                        Box(
                            modifier = Modifier
                                .widthIn(min = 150.dp)
                                .heightIn(min = 150.dp)
                                .background(color = Blue200)
                        )
                    }
                }
            })
        }

    }
}
@Preview
@Composable
internal fun FeedScreenPreview() {
    FeedScreen()
}