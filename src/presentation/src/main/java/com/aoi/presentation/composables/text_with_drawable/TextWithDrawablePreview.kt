package com.aoi.presentation.composables.text_with_drawable

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aoi.presentation.R

/**
 * PreviewLight
 *
 * ライトテーマでのプレビュー用コンポーザブル関数
 */
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
fun PreviewLight(){
    TextWithDrawable(drawableId = R.drawable.verified_user_16px, content = "aoi-stella")
}

/**
 * PreviewDark
 *
 * ダークテーマでのプレビュー用コンポーザブル関数
 */
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun PreviewDark(){
    TextWithDrawable(drawableId = R.drawable.verified_user_16px, content = "aoi-stella")
}