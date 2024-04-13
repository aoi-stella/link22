package com.aoi.data.timeline

/**
 * タイムラインのデータクラス
 *
 * @param title タイトル
 * @param article 要約
 * @param url URL
 */
data class TimelineDataClass(
    //タイトル
    val title: String,
    //要約
    val article: String,
    //URL
    val url: String
)