package com.tinnovakovic.videostreamer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tinnovakovic.videostreamer.ui.home.homeScreen
import com.tinnovakovic.videostreamer.ui.lesson.lessonScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Scaffold { innerPadding ->
                navManager.commands.collectAsState().value.also { command ->
                    if (command.destinationRoute.isNotEmpty()) navController.navigate(command.destinationRoute)
                }
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home.name,
                    Modifier.padding(innerPadding)
                ) {
                    homeScreen()
                    lessonScreen()
                }
            }
        }
    }
}
