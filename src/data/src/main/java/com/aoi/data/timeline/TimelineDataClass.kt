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
 * @param translatedTitle 翻訳後のタイトル
 * @param translatedArticle 翻訳後の要約
 * @param thumbnailImagePath サムネイル画像パス
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
    val publishFrom: String,
    //翻訳後のタイトル
    val translatedTitle: String,
    //翻訳後の要約
    val translatedArticle: String,
    //サムネイル画像パス
    val thumbnailImagePath: String
)