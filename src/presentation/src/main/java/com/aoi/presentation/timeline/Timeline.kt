package com.aoi.presentation.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    //サムネイル画像パスリスト
    val thumbnailImagePathList = vm.thumbnailImagePathList.collectAsState().value

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
                        thumbnailImageUri = thumbnailImagePathList[it],
                        cardClickedEvent = { vm.onClickedEventArticleCard(uriList[it]) },
                        translateClickedEvent = { vm.onClickedEventTranslateButton(it) }
                    )
                }
            }
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
 * @param thumbnailImageUri サムネイル画像URI
 * @param cardClickedEvent カードクリックイベント
 * @param translateClickedEvent 翻訳クリックイベント
 */
fun ArticleCard(
    title: String,
    article: String,
    date: String,
    publisher: String,
    thumbnailImageUri: String,
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
        ThumbnailImage(thumbnailImageUri)
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

/**
 * サムネイル画像の描画
 * @param url 画像のURL
 */
@Composable
fun ThumbnailImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
    )
}

/**
 * タイムライン画面のプレビュー
 */
@Preview
@Composable
fun PreviewArticleCard() {
    ArticleCard(
        title = "Researchers Detail Multistage Attack Hijacking Systems with SSLoad, Cobalt Strike",
        article = "Cybersecurity researchers have discovered an ongoing attack campaign&nbsp;that's&nbsp;leveraging phishing emails to deliver a malware called SSLoad.The campaign, codenamed&nbsp;FROZEN#SHADOW&nbsp;by Securonix, also involves&nbsp;the deployment of&nbsp;Cobalt Strike and the ConnectWise ScreenConnect remote desktop software.\"SSLoad is designed to stealthily infiltrate systems, gather sensitive",
        date = "2024/04/24",
        publisher = "TheHackersNews",
        thumbnailImageUri = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhH1bEFQuUUnkFd5aJJzA0-_GeF9ciA5uY4iOf4gb7sJD_azqgIvlCw850DISUSIsP2TIus9AFy9KKFZSRgzFzJeB6KpIR9f0ttNsPYZiNILooejl8n2rVGQVziOWkf-9i2xtdx55PFylzHAGwMPp-H7vi9PCouncZWA0wY5B2VmBK1UtQxdkWy1vB7jfV5/s1600/hacke.png",
        cardClickedEvent = {},
        translateClickedEvent = {}
    )
}