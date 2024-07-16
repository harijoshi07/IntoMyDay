package com.example.trackify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trackify.TrackifyApp
import com.example.trackify.TaskViewModel
import com.example.trackify.presentation.add_edit_screen.AddTaskScreen
import com.example.trackify.presentation.add_edit_screen.EditTaskScreen
import com.example.trackify.presentation.completed_task_screen.CompletedTaskScreen
import com.example.trackify.presentation.timer_screen.PomodoroScreen

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TrackifyScreen.name) {
        composable(Routes.TrackifyScreen.name) {
            //HomeScreen(viewModel)
            val tasks by taskViewModel.taskList.collectAsState()
            TrackifyApp(
                tasks,
                onEvent = taskViewModel::onEvent,
                onAddTask = {
                    navController.navigate(route = Routes.AddTaskScreen.name)
                },
                onEditTask = { id ->
                    navController.navigate(route = "${Routes.EditTaskScreen.name}/$id")
                },
                onClickCompletedInfo = {
                    navController.navigate(route = Routes.CompletedTaskScreen.name)
                },
                onPomodoroTask = {id->
                    navController.navigate(route = "${Routes.PomodoroScreen.name}/$id")
                }
            )
        }

        composable(route = Routes.AddTaskScreen.name) {
            AddTaskScreen(
                onEvent = taskViewModel::onEvent,
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
                LaunchedEffect(key1 = true) {
                    taskViewModel.getTaskById(id!!)

                }
                EditTaskScreen(
                    task = taskViewModel.task,
                    onEvent = taskViewModel::onEvent,
                    onBack = { navController.popBackStack() },
                )
            }
        }

        composable(route = Routes.CompletedTaskScreen.name) {
            val tasks by taskViewModel.taskList.collectAsState()
            CompletedTaskScreen(
                tasks = tasks,
                onEvent = taskViewModel::onEvent,
                onClose = { navController.popBackStack() }
            )
        }
        
        composable(
            route="${Routes.PomodoroScreen.name}/{id}",
            arguments = listOf(navArgument("id"){
                type=NavType.IntType
            })
        )
        {navBackStackEntry->
            navBackStackEntry.arguments?.getInt("id").let { id->
                LaunchedEffect(key1 = true) {
                    taskViewModel.getTaskById(id!!)
                }
                PomodoroScreen(
                    task = taskViewModel.task,
                    onBack = {navController.popBackStack()}
                )
            }
        }
    }
}