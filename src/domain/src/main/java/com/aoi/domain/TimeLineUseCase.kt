package com.aoi.domain

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.aoi.data.timeline.TimeLineRepository
import com.aoi.data.timeline.TimelineDataClass

/**
 * Timeline画面用のUseCaseクラス
 * as Model
 *
 * TODO: DI化していきたいです
 */
class TimeLineUseCase(
    private val repos: TimeLineRepository = TimeLineRepository()
) {
    /**
     * 記事を取得する
     *
     * @return List<TimelineDataClass> 記事のリスト
     *
     * @throws Exception 記事の取得に失敗した場合
     * @throws Exception 記事が0件の場合
     */
    suspend fun fetchArticles(): List<TimelineDataClass> {
        val result = repos.getArticles()
        //記事取得結果が失敗の場合は例外を投げる
        if (result.isFailure) {
            throw Exception(TimeLineUseCaseException.GET_ARTICLES_FAILED)
        }

        /**
         * 記事取得結果が0件(null)の場合は例外を投げて、
         * それ以外の場合は取得した記事を返す
         */
        return result.getOrNull() ?: throw Exception(TimeLineUseCaseException.NO_ARTICLES)
    }

    /**
     * 記事のURLを開く
     *
     * @param url String URL
     */
    fun openDetailPage(ctx: Context,url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx.startActivity(intent)
    }

    /**
     * テキストを翻訳する
     *
     * @return String 翻訳されたテキスト
     */
    fun translateText(): String{
        //TODO: 翻訳処理を実装する。Google Cloud使うかライブラリ使うか
        return "Hello"
    }
}