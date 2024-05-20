package com.example.trackify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trackify.TrackifyApp
import com.example.trackify.ui.components.TaskViewModel
import com.example.trackify.ui.screen.HomeScreen

@Composable
fun AppNavigation(viewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TrackifyScreen.name) {
        composable(Routes.TrackifyScreen.name) {
            //HomeScreen(viewModel)
            TrackifyApp(viewModel)
        }
    }

}