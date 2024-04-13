package com.aoi.presentation.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aoi.domain.TimeLineUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Timeline画面用のViewModelクラス
 *
 * @param useCase TimeLine画面のユースケースクラス
 */
class TimeLineViewModel(
    private val useCase: TimeLineUseCase = TimeLineUseCase()
): ViewModel() {
    //記事本数
    private val _articleCount = MutableStateFlow(0)
    val articleCount: StateFlow<Int> = _articleCount
    //タイトルリスト
    private val _titleList = MutableStateFlow<List<String>>(emptyList())
    val titleList: StateFlow<List<String>> = _titleList
    //記事リスト
    private val _articleList = MutableStateFlow<List<String>>(emptyList())
    val articleList: StateFlow<List<String>> = _articleList
    //出版日リスト
    private val _publishDateList = MutableStateFlow<List<String>>(emptyList())
    val publishDateList: StateFlow<List<String>> = _publishDateList
    //出版社リスト
    private val _publisherList = MutableStateFlow<List<String>>(emptyList())
    val publisherList: StateFlow<List<String>> = _publisherList

    //イニシャライザ
    init {
        //記事を取得する
        fetchArticles()
    }

    /**
     * 記事を更新する
     */
    private fun fetchArticles(){
        viewModelScope.launch {
            try{
                val articles = useCase.fetchArticles()
                _articleCount.value = articles.size
                articles.forEach {
                    _titleList.value += it.title
                    _articleList.value += it.article
                    _publishDateList.value += it.publishDate
                    _publisherList.value += it.publisher
                }
            }
            catch (e: Exception){
                //TODO: 例外処理書いてくれー。とりあえずトーストでエラーメッセージ表示
            }
        }
    }
}