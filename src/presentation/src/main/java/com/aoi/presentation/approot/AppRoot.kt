package com.aoi.presentation.approot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.R
import com.aoi.presentation.approot.ui.theme.Link22Theme

class AppRootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Link22Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GlobalBottomNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = R.string.nav_destination_timeline.toString()
){
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ){
        composable(R.string.nav_destination_timeline.toString()){
            //TODO: Add timeline screen
        }
        //TODO: Add other screens
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Link22Theme {
        Greeting("Android")
    }
}