package com.aoi.presentation.composables.text_with_drawable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aoi.presentation.R

@Composable
fun TextWithDrawable(
    drawableId: Int,
    drawableTInt: Color = Color.Unspecified,
    content: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.wrapContentWidth()
    ){
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = drawableTInt,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Text(
            text = content,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 5.dp),
            textAlign = TextAlign.Center
        )
    }
}