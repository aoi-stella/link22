package com.aoi.presentation.composables.text_with_drawable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextWithDrawable(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.wrapContentWidth()
    ){
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = content,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )
    }
}