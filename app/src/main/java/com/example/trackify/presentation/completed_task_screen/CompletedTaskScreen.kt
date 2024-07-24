package com.example.trackify.presentation.completed_task_screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.h1TextStyle
import com.example.trackify.presentation.home_screen.HomeScreenEvent
import com.example.trackify.presentation.home_screen.components.EmptyScreenComponent
import com.example.trackify.presentation.home_screen.components.TaskComponent
import com.example.trackify.ui.theme.TrackifyTheme
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CompletedTaskScreen(
    tasks: List<Task>,
    onEvent: (HomeScreenEvent) -> Unit,
    onClose: () -> Unit
) {

    val completedTasks = mutableListOf<Task>()
    tasks.filterTo(completedTasks) { it.isCompleted }

    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.padding(8.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title = {
                Text(
                    text = "Completed Tasks",
                    style = h1TextStyle
                )
            },
            navigationIcon = {
                IconButton(onClick = { onClose() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
        )
    }) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            if (completedTasks.isEmpty()) {
                EmptyScreenComponent()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            16.dp,
                            0.dp
                        )
                ) {
                    items(items = completedTasks,
                        key = { it.id }) { task ->
                        Box(modifier = Modifier.animateItemPlacement()){
                            TaskComponent(
                                task = task,
                                onUpdate = {},
                                onComplete = {
                                    onEvent(
                                        HomeScreenEvent.OnCompleted(
                                            it,
                                            false
                                        )
                                    )
                                },
                                onPomodoro = {}
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CompletedTaskScreenPreview() {
    TrackifyTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        val tasks = listOf(
            Task(
                id = 1,
                title = "Learn Kotlin",
                isCompleted = true,
                startTime = LocalTime.now(),
                endTime = LocalTime.now(),
                reminder = true,
                category = "",
                priority = 0
            ),
            Task(
                id = 2,
                title = "Drink Water",
                isCompleted = true,
                startTime = LocalTime.now(),
                endTime = LocalTime.now(),
                reminder = false,
                category = "",
                priority = 1
            )
        )
        CompletedTaskScreen(
            tasks = tasks,
            {},
            {}
        )
    }
}