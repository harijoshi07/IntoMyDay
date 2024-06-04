package com.example.trackify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trackify.TrackifyApp
import com.example.trackify.ui.home_screen.TaskViewModel
import com.example.trackify.ui.add_edit_screen.AddTaskScreen
import com.example.trackify.ui.add_edit_screen.EditTaskScreen

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TrackifyScreen.name) {
        composable(Routes.TrackifyScreen.name) {
            //HomeScreen(viewModel)
            val tasks by taskViewModel.taskList.collectAsState()
            TrackifyApp(
                tasks,
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