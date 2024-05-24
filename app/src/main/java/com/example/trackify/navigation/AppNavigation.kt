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
import com.example.trackify.ui.screen.AddTaskScreen
import com.example.trackify.ui.screen.EditTaskScreen
import com.example.trackify.ui.screen.HomeScreen

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TrackifyScreen.name) {
        composable(Routes.TrackifyScreen.name) {
            //HomeScreen(viewModel)
            TrackifyApp(
                taskViewModel,
                onAddTask = {
                    navController.navigate(route = Routes.AddTaskScreen.name)
                },
                onEditTask = { id ->
                    navController.navigate(route = "${Routes.EditTaskScreen.name}/$id")
                }
            )
        }

        composable(route = Routes.AddTaskScreen.name) {
            AddTaskScreen(
                taskViewModel = taskViewModel,
                onClose = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.EditTaskScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                EditTaskScreen(
                    taskViewModel = taskViewModel,
                    onBack = { navController.popBackStack() },
                    taskId = id!!
                )
            }
        }
    }

}