package com.aoi.presentation.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoi.presentation.R
import com.aoi.presentation.composables.text_with_drawable.TextWithDrawable

@Composable
/**
 * タイムライン画面
 * as View
 *
 * @param vm ViewModel
 */
fun Timeline(
    vm: ViewModel = viewModel()
) {
    //記事本数
    val articleCount = vm.articleCount.collectAsState().value
    //タイトルリスト
    val titleList = vm.titleList.collectAsState().value
    //記事リスト
    val articleList = vm.articleList.collectAsState().value
    //出版日リスト
    val publishDateList = vm.publishDateList.collectAsState().value
    //出版社リスト
    val publisherList = vm.publisherList.collectAsState().value
    //uriリスト
    val uriList = vm.urlList.collectAsState().value

    //コンテキストセット
    vm.setContextFromView(LocalContext.current)

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            items(articleCount) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ArticleCard(
                        //各リストの要素を取得
                        title = titleList[it],
                        article = articleList[it],
                        date = publishDateList[it],
                        publisher = publisherList[it],
                        cardClickedEvent = { vm.onClickedEventArticleCard(uriList[it]) },
                        translateClickedEvent = { vm.onClickedEventTranslateButton(it) }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = { /* フローティングボタンのクリックアクション */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Composable
/**
 * 記事カード
 *
 * @param title タイトル
 * @param article 記事
 * @param date 出版日
 * @param publisher 出版社
 * @param cardClickedEvent カードクリックイベント
 * @param translateClickedEvent 翻訳クリックイベント
 */
fun ArticleCard(
    title: String,
    article: String,
    date: String,
    publisher: String,
    cardClickedEvent : () -> Unit,
    translateClickedEvent : () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .width(350.dp)
            .wrapContentHeight()
            .clickable { cardClickedEvent() }
    ) {
        //タイトル
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        )
        //記事
        Text(
            text = article,
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        )
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(top = 25.dp, bottom = 25.dp)
            ) {
                //出版日
                TextWithDrawable(
                    drawableId = R.drawable.ic_calendar_16px,
                    content = date,
                    modifier = Modifier.padding(start = 10.dp)
                )
                //出版社
                TextWithDrawable(
                    drawableId = R.drawable.verified_user_16px,
                    content = publisher,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            IconButton(
                onClick = { translateClickedEvent() },
                modifier = Modifier.padding(start = 25.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_translate_24px),
                    contentDescription = null,
                )
            }
        }
    }
}