package com.aoi.util.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * BottomNavigationItem
 *
 * @property title アイテムの名前
 * @property selectedIcon 選択時のアイコン
 * @property unselectedIcon 非選択時のアイコン
 * @property doNotify 通知を行うかどうか
 * @property notifyCount 通知の数
 */
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val doNotify: Boolean = false,
    val notifyCount: Int = 0
)