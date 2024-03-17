package com.aoi

import android.app.Application
import com.aoi.data.api.firebase.FirebaseAPI
import com.google.firebase.FirebaseApp

/**
 * ApplicationEntry
 *
 * アプリケーションのエントリーポイント
 */
class ApplicationEntry: Application() {
    override fun onCreate() {
        super.onCreate()
        // Firebase初期化
        FirebaseApp.initializeApp(this)
        FirebaseAPI.init()
    }
}