package com.aoi.presentation.timeline

/**
 * タイムライン画面の状態
 */
data class UiState(
    val articleCount: Int = 0,
    val titleList: List<String> = emptyList(),
    val articleList: List<String> = emptyList(),
    val publishDateList: List<String> = emptyList(),
    val publisherList: List<String> = emptyList(),
    val urlList: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)