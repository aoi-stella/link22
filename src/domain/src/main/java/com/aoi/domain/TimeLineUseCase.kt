package com.aoi.domain

import com.aoi.data.timeline.TimeLineRepository
import com.aoi.data.timeline.TimelineDataClass

/**
 * Timeline画面用のUseCaseクラス
 * as Model
 */
class TimeLineUseCase(
    private val repos: TimeLineRepository = TimeLineRepository(),
    // private val infra: TimelineInfrastructure = TimelineInfrastructure()
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
    fun openDetailPage(url: String){
        // infra.openDetailPage(url)
    }
}