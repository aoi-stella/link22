package com.aoi.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aoi.presentation.settings.ui.theme.Link22Theme

@Composable
fun Settings() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            appSection()
        }

    }
}

@Composable
private fun appSection(){
    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        //セクション名
        Text(
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.Start),
            text = "About application",
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        )
        displayItem("Build Version", "1.0.0")
        displayItem("Developer", "aoi")
    }
}

@Composable
private fun displayItem(header: String, content: String){
    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        //ヘッダ部分
        Text(
            modifier = Modifier
                .align(alignment = androidx.compose.ui.Alignment.Start)
                .padding(0.dp, 5.dp, 0.dp, 0.dp),
            text = header,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        //コンテンツ部分
        Text(
            modifier = Modifier
                .align(alignment = androidx.compose.ui.Alignment.Start)
                .padding(0.dp, 0.dp, 0.dp, 5.dp),
            text = content,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Link22Theme {
        Settings()
    }
}