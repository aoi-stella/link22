package com.aoi.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aoi.presentation.R
import com.aoi.presentation.approot.ui.theme.Link22Theme

/**
 * Settings
 * 設定画面
 */
@Composable
fun Settings() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            AppSection()
        }

    }
}

/**
 * AppSection
 * アプリケーションについてのセクション
 */
@Composable
private fun AppSection(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.background)
    ){
        //セクション名
        DisplayTitle("アプリケーション")
        DisplayItem(ImageVector.vectorResource(R.drawable.ic_apps_24px), "バージョン", "1.0.0")

        DisplayTitle("連絡先")
        DisplayItem(ImageVector.vectorResource(R.drawable.ic_develop_24px), "開発者", "aoi")
        DisplayItem(ImageVector.vectorResource(R.drawable.ic_contact_24px), "X", "@aoi_sec")
        DisplayItem(ImageVector.vectorResource(R.drawable.ic_contact_24px), "Instagram", "@aoi.sec")
        DisplayItem(ImageVector.vectorResource(R.drawable.ic_contact_24px), "Email", "aoi.stella.dev@gmail.com")
    }
}

/**
 * displayTitle
 * タイトル表示
 * @param text String
 */
@Composable
private fun DisplayTitle(text:String){
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = text,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        color = MaterialTheme.colorScheme.primary,
        style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    )
}

/**
 * DisplayItem
 * 表示項目
 * @param header String
 * @param content String
 */
@Composable
private fun DisplayItem(iconImage: ImageVector, header: String, content: String){
    Row(modifier = Modifier .fillMaxWidth() ){
        Column(modifier = Modifier.fillMaxWidth()) {
            Divider(
                color = Color.Gray,
                thickness = 1.5f.dp,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = iconImage,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                    )
                    //ヘッダ部分
                    Text(
                        text = header,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                //コンテンツ部分
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        .padding(end = 24.dp),
                    text = content,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}

/**
 * Preview
 * プレビュー
 */
@Preview(showBackground = true)
@Composable
fun Preview() {
    Link22Theme {
        Settings()
    }
}