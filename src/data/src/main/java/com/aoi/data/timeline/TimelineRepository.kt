package com.aoi.data.timeline

import com.aoi.data.api.firebase.FirebaseAPI

/**
 * Timeline画面用のリポジトリ
 *
 * @property timelineLocalDataSource ローカルデータソース
 * @property timelineRemoteDataSource リモートデータソース
 */
class TimeLineRepository(
    private val timelineLocalDataSource: TimelineLocalDataSource = TimelineLocalDataSource(),
    private val timelineRemoteDataSource: TimelineRemoteDataSource = TimelineRemoteDataSource(FirebaseAPI)
) {

    /**
     * 記事を取得する
     *
     * @return Result<List<TimelineDataClass>> 記事のリスト
     *
     * TODO: オフライン時やキャッシュ時の挙動を考慮すること。
     */
    suspend fun getArticles(): Result<List<TimelineDataClass>> {
        return timelineRemoteDataSource.getArticlesFromFirebase()
    }
}