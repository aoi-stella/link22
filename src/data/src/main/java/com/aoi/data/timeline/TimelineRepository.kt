package com.aoi.data.timeline

/**
 * Timeline画面用のリポジトリ
 *
 * @property timelineLocalDataSource ローカルデータソース
 * @property timelineRemoteDataSource リモートデータソース
 */
class TimelineRepository(
    private val timelineLocalDataSource: TimelineLocalDataSource,
    private val timelineRemoteDataSource: TimelineRemoteDataSource
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