package com.aoi.presentation.approot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.R
import com.aoi.presentation.approot.ui.theme.Link22Theme
import com.aoi.presentation.settings.Settings
import com.aoi.presentation.timeline.Timeline
import com.aoi.util.entity.BottomNavigationItem

/**
 * AppRootActivity
 * アプリケーションのルートとなるActivity
 */
class AppRootActivity : ComponentActivity() {
    /**
     * onCreate
     * アクティビティの生成
     * @param savedInstanceState Bundle?
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(
            Color.DarkGray.toArgb())
        )

        setContent {
            Link22Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }

    /**
     * Screen
     * 画面描画
     */
    @Composable
    fun Screen(){
        val navController = rememberNavController()
        val items = listOf(
            BottomNavigationItem(
                title = getString(R.string.nav_destination_articles),
                route = getString(R.string.nav_destination_articles),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                title = getString(R.string.nav_destination_about),
                route = getString(R.string.nav_destination_about),
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings)
        )
        // NavControllerの現在のバックスタックエントリをリアクティブに監視
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val topTitle = when (currentBackStackEntry?.destination?.route) {
            getString(R.string.nav_destination_articles) -> getString(R.string.nav_destination_articles)
            getString(R.string.nav_destination_about) -> getString(R.string.nav_destination_about)
            else -> ""
        }

        Scaffold(
            topBar = { AppTopBar(topTitle) },
            bottomBar = { AppNavigationBar(navController = navController, items = items) }
        ){
            innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                NavHost(navController = navController, startDestination = getString(R.string.nav_destination_articles)){
                    composable(getString(R.string.nav_destination_articles)){
                        Timeline()
                    }
                    composable(getString(R.string.nav_destination_about)){
                        Settings()
                    }
                }
            }
        }
    }

    /**
     * AppTopBar
     * アプリケーションのトップバー
     * @param title String
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppTopBar(
        title: String
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            /*navigationIcon = {
                //TODO: 若干アイコン左側過ぎない？
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation Icon",
                    modifier = Modifier.clickable(enabled = false, onClick = {})
                )
            }*/
        )
    }

    /**
     * AppNavigationBar
     * アプリケーションのナビゲーションバー
     * @param navController NavHostController
     * @param items List<BottomNavigationItem>
     */
    @Composable
    fun AppNavigationBar(
        navController: NavHostController = rememberNavController(),
        items: List<BottomNavigationItem>
    ){
        //TODO: ナビゲーションアイテム切り替え時の挙動が少し重い
        NavigationBar{
            // NavControllerの現在のバックスタックエントリをリアクティブに監視
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            // 現在のルートを取得
            val currentRoute = currentBackStackEntry?.destination?.route
            items.forEach {
                    item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}