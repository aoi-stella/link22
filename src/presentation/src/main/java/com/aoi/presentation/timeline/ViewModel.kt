package com.aoi.presentation.timeline

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aoi.domain.TimeLineUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Timeline画面用のViewModelクラス
 * as ViewModel
 *
 * @param useCase TimeLine画面のユースケースクラス
 */
class ViewModel(
    private val useCase: TimeLineUseCase = TimeLineUseCase()
): ViewModel() {
    //記事本数
    private val _articleCount = MutableStateFlow(0)
    val articleCount: StateFlow<Int> = _articleCount
    //原文のタイトルリスト
    private val _originTitleList = MutableStateFlow<List<String>>(emptyList())
    //翻訳後のタイトルリスト
    private val _translatedTitleList = MutableStateFlow<List<String>>(emptyList())
    //表示するタイトルリスト
    private val _titleList = MutableStateFlow<List<String>>(emptyList())
    val titleList: StateFlow<List<String>> = _titleList
    //原文の記事リスト
    private val _originArticleList = MutableStateFlow<List<String>>(emptyList())
    //翻訳後の記事リスト
    private val _translatedArticleList = MutableStateFlow<List<String>>(emptyList())
    //表示する記事リスト
    private val _articleList = MutableStateFlow<List<String>>(emptyList())
    val articleList: StateFlow<List<String>> = _articleList
    //出版日リスト
    private val _publishDateList = MutableStateFlow<List<String>>(emptyList())
    val publishDateList: StateFlow<List<String>> = _publishDateList
    //出版社リスト
    private val _publisherList = MutableStateFlow<List<String>>(emptyList())
    val publisherList: StateFlow<List<String>> = _publisherList
    //urlリスト
    private val _urlList = MutableStateFlow<List<String>>(emptyList())
    val urlList: StateFlow<List<String>> = _urlList
    //サムネイル画像パスリスト
    private val _thumbnailImagePathList = MutableStateFlow<List<String>>(emptyList())
    val thumbnailImagePathList: StateFlow<List<String>> = _thumbnailImagePathList

    //コンテキスト
    @SuppressLint("StaticFieldLeak")
    private lateinit var ctx: Context

    //翻訳フラグ
    private var translateToJA: Boolean = true

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
                    _originTitleList.value += it.title
                    _titleList.value += it.title
                    _articleList.value += it.article.replace("&nbsp;", " ")
                    _originArticleList.value += it.article.replace("&nbsp;", " ")
                    _publishDateList.value += it.publishDate
                    _publisherList.value += it.publisher
                    _urlList.value += it.url
                    _translatedTitleList.value += it.translatedTitle
                    _translatedArticleList.value += it.translatedArticle
                    _thumbnailImagePathList.value += it.thumbnailImagePath
                }
            }
            catch (e: Exception){
                //TODO: 例外処理書いてくれー。とりあえずトーストでエラーメッセージ表示
            }
        }
    }

    /**
     * 記事カードがクリックされた時の処理
     */
    fun onClickedEventArticleCard(url: String){
        useCase.openDetailPage(this.ctx, url)
    }

    /**
     * 翻訳ボタンがクリックされた時の処理
     * @param index クリックされた記事のインデックス
     */
    fun onClickedEventTranslateButton(index: Int){
        val dstTitleList: List<String>
        val dstArticleList: List<String>

        // 日本語→英語の場合
        if(!translateToJA){
            dstTitleList = _originTitleList.value
            dstArticleList = _originArticleList.value
            translateToJA = true
        }
        // 英語→日本語の場合
        else{
            dstTitleList = _translatedTitleList.value
            dstArticleList = _translatedArticleList.value
            translateToJA = false
        }

        // タイトルと記事を更新
        _titleList.value = _titleList.value.toMutableList().apply {
            this[index] = dstTitleList[index]
        }
        _articleList.value = _articleList.value.toMutableList().apply {
            this[index] = dstArticleList[index]
        }
    }

    /**
     * コンテキストをViewから取得する
     *
     * @param ctx コンテキスト
     */
    fun setContextFromView(ctx: Context){
        this.ctx = ctx
    }
}