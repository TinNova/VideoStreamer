package com.tinnovakovic.videostreamer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tinnovakovic.videostreamer.ui.home.HomeScreen
import com.tinnovakovic.videostreamer.ui.home.HomeViewModel
import com.tinnovakovic.videostreamer.ui.home.homeScreen
import com.tinnovakovic.videostreamer.ui.lesson.LessonScreen
import com.tinnovakovic.videostreamer.ui.lesson.LessonViewModel
import com.tinnovakovic.videostreamer.ui.lesson.lessonScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Scaffold { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home.name,
                    Modifier.padding(innerPadding)
                ) {
                    homeScreen(navController)
                    lessonScreen(navController)
                }
            }
        }
    }
}
