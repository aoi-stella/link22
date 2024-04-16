package com.aoi.data.timeline

/**
 * タイムラインのデータクラス
 * as Model
 *
 * @param title タイトル
 * @param article 要約
 * @param url URL
 * @param publishDate 出版日
 * @param publisher 出版社
 * @param publishFrom RSSのURL
 */
data class TimelineDataClass(
    //タイトル
    val title: String,
    //要約
    val article: String,
    //URL
    val url: String,
    //出版日
    val publishDate: String,
    //出版社
    val publisher: String,
    //RSSのURL
    val publishFrom: String
)