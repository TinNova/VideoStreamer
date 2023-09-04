package com.tinnovakovic.videostreamer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tinnovakovic.videostreamer.ui.home.HomeScreen
import com.tinnovakovic.videostreamer.ui.home.HomeViewModel
import com.tinnovakovic.videostreamer.ui.lesson.LessonScreen
import com.tinnovakovic.videostreamer.ui.lesson.LessonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Surface {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home.name
                ) {
                    composable(Destination.Home.name) {
                        HomeScreen(viewModel = hiltViewModel<HomeViewModel>(), navController)
                    }
                    composable("${Destination.Lesson.name}/{subjectTitle}") {
                        LessonScreen(viewModel = hiltViewModel<LessonViewModel>(), navController)
                    }
                }
            }
        }
    }
}
