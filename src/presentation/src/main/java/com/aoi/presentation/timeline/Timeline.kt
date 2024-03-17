package com.aoi.presentation.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aoi.presentation.R
import com.aoi.presentation.composables.text_with_drawable.TextWithDrawable
import com.aoi.presentation.timeline.ui.theme.Link22Theme

@Composable
fun Timeline() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        items(1) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArticleCard()
            }
        }
    }
}

@Composable
fun ArticleCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .width(350.dp)
            .wrapContentHeight()
    ) {
        //タイトル
        Text(
            text = "Third-Party ChatGPT Plugins Could Lead to Account Takeovers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        )
        //記事
        Text(
            text = "Cybersecurity researchers have found that third-party plugins available for OpenAI ChatGPT could act as a new attack surface for threat actors looking to gain unauthorized access to sensitive data.According to&nbsp;new research&nbsp;published by Salt Labs, security flaws found directly in ChatGPT and within the ecosystem could allow attackers to install malicious plugins without users' consent",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp)){
            TextWithDrawable(
                drawableId = R.drawable.ic_calendar_16px,
                content = "2021/10/10",
                modifier = Modifier.padding(start = 10.dp)
            )
            TextWithDrawable(
                drawableId = R.drawable.verified_user_16px,
                drawableTInt = Color.Cyan,
                content = "The Hacker News",
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Link22Theme {
        Timeline()
    }
}