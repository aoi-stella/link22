package com.aoi.data.timeline

import com.aoi.data.api.firebase.FirebaseAPI
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Timeline画面用のリモートデータソースクラス
 * as Model
 *
 * @param firebaseApi FirebaseAPI
 */
class TimelineRemoteDataSource(private val firebaseApi: FirebaseAPI) {

    companion object {
        // Firebaseのコレクション名
        private const val FIREBASE_ARTICLE_COLLECTION_NAME = "articles"

        // Firebaseのドキュメントのキー[タイトル]
        private const val FIREBASE_ARTICLE_TITLE_KEY = "title"

        // Firebaseのドキュメントのキー[概要]
        private const val FIREBASE_ARTICLE_SUMMARY_KEY = "summary"

        // Firebaseのドキュメントのキー[URL]
        private const val FIREBASE_ARTICLE_LINK_KEY = "link"

        // Firebaseのドキュメントのキー[出版日]
        private const val FIREBASE_ARTICLE_PUBLISH_DATE_KEY = "publishDate"

        // Firebaseのドキュメントのキー[出版社]
        private const val FIREBASE_ARTICLE_PUBLISHER_KEY = "publisher"

        // Firebaseのドキュメントキー[rssURL]
        private const val FIREBASE_ARTICLE_RSS_URL_KEY = "publishFrom"

        // Firebaseのドキュメントキー[翻訳後のタイトル]
        private const val FIREBASE_ARTICLE_TRANSLATED_TITLE_KEY = "translated_title"

        // Firebaseのドキュメントキー[翻訳後の概要]
        private const val FIREBASE_ARTICLE_TRANSLATED_SUMMARY_KEY = "translated_summary"

        // Firebaseのドキュメントキー[サムネイル画像パス]
        private const val FIREBASE_ARTICLE_THUMBNAIL_IMAGE_PATH_KEY = "thumbnailPath"

        // Firebaseのドキュメントキー[タグ]
        private const val FIREBASE_ARTICLE_TAG_KEY = "tag"
    }

    /**
     * Firebaseから記事一覧を取得する
     *
     * @return Result<List<TimelineDataClass>> Firebaseから取得した記事一覧
     */
    suspend fun getArticlesFromFirebase(): Result<List<TimelineDataClass>> =
        suspendCoroutine { continuation ->
            firebaseApi.readAllDocument(FIREBASE_ARTICLE_COLLECTION_NAME) { result ->
                val mappedResult = result.mapCatching { documents ->
                    documents.map { document ->
                        TimelineDataClass(
                            title = document[FIREBASE_ARTICLE_TITLE_KEY] as? String ?: "",
                            article = document[FIREBASE_ARTICLE_SUMMARY_KEY] as? String ?: "",
                            url = document[FIREBASE_ARTICLE_LINK_KEY] as? String ?: "",
                            publishDate = document[FIREBASE_ARTICLE_PUBLISH_DATE_KEY] as? String ?: "",
                            publisher = document[FIREBASE_ARTICLE_PUBLISHER_KEY] as? String ?: "",
                            publishFrom = document[FIREBASE_ARTICLE_RSS_URL_KEY] as? String ?: "",
                            translatedTitle = document[FIREBASE_ARTICLE_TRANSLATED_TITLE_KEY] as? String ?: "",
                            translatedArticle = document[FIREBASE_ARTICLE_TRANSLATED_SUMMARY_KEY] as? String ?: "",
                            thumbnailImagePath = document[FIREBASE_ARTICLE_THUMBNAIL_IMAGE_PATH_KEY] as? String ?: "",
                            tag = document[FIREBASE_ARTICLE_TAG_KEY] as? String ?: ""
                        )
                    }
                }
                continuation.resume(mappedResult)
            }
        }
}