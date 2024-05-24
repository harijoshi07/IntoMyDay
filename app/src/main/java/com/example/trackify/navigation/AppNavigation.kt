package com.example.trackify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trackify.TrackifyApp
import com.example.trackify.ui.components.TaskViewModel
import com.example.trackify.ui.screen.AddEditScreen

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TrackifyScreen.name) {
        composable(Routes.TrackifyScreen.name) {
            //HomeScreen(viewModel)
            TrackifyApp(
                taskViewModel,
                onAddEdit={id->
                    navController.navigate(route="${Routes.AddEditScreen.name}/$id")
                }
            )
        }
        composable(
            route = "${Routes.AddEditScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                AddEditScreen(
                    taskViewModel = taskViewModel,
                    onBack = { navController.popBackStack() },
                    taskId = id!!
                )
            }
        }
    }

}