package com.aoi.data.timeline

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * TODO: DIでContextを渡す
 * Timeline画面用のインフラストラクチャクラス
 * as Model
 *
 * @param ctx Context コンテキスト
 */
class TimelineInfrastructure(
    private val ctx: Context
) {
    /**
     * URLを開く
     *
     * @param url String URL
     */
    fun openDetailPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx.startActivity(intent)
    }
}