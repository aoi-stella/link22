package com.aoi;

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * ApplicationEntry
 *
 * アプリケーションのエントリーポイント
 */
class ApplicationEntry: Application() {
    override fun onCreate() {
        super.onCreate()
        // Firebase の明示的な初期化
        FirebaseApp.initializeApp(this)
    }
}