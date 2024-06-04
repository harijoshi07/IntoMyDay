package com.example.trackify.ui.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.R
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.ui.home_screen.components.EmptyScreenComponent
import com.example.trackify.ui.home_screen.components.InfoComponent
import com.example.trackify.ui.home_screen.components.TaskComponent
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.TrackifyTheme
import java.time.LocalTime

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onEvent: (HomeScreenEvent) -> Unit,
    onEditTask: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val completedTasks = mutableListOf<Task>()
    val incompletedTasks = mutableListOf<Task>()

    tasks.filterTo(completedTasks) { it.isCompleted }
    tasks.filterTo(incompletedTasks) { !it.isCompleted }

    val totalTasks = tasks.size
    val totalCompletedTasks = completedTasks.size


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
            //.padding(12.dp)
        ) {
            InfoComponent(
                title = "Completed",
                desc = "$totalCompletedTasks/$totalTasks",
                icon = R.drawable.ic_task_list,
                backgroundColor = Green,
                modifier = Modifier.weight(1f)

            )

            InfoComponent(
                title = "Free Time",
                desc = "3 hours",
                icon = R.drawable.ic_clock,
                backgroundColor = Blue,
                modifier = Modifier.weight(1f)

            )
        }


        if (incompletedTasks.isEmpty()) {
            EmptyScreenComponent()
        } else {
            Text(
                text = stringResource(R.string.today_s_task),
                style = h2TextStyle,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp)
            )

            //items() fun is used to fill the Column with data
            //contains two parameters: items and key
            //items: list of items to be displayed
            //key: unique identifier for each item
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 0.dp)
            ) {
                items(items = incompletedTasks, key = { it.id }) { it ->
                    TaskComponent(
                        task = it,
                        onUpdate = onEditTask,
                        onComplete = { it ->
//                            taskViewModel.getTaskById(taskId)
//                            taskViewModel.updateIsCompleted(isCompleted =!taskViewModel.task.isCompleted)
//                            taskViewModel.updateTask(taskViewModel.task)
                            onEvent(HomeScreenEvent.OnCompleted(it, true))

                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }

}

@Preview
@Composable
private fun PrevHomeScreen() {
    TrackifyTheme(darkTheme = true, dynamicColor = false) {
        // HomeScreen()
        val tasks = listOf(
            Task(
                id = 1,
                title = "Learn Kotlin",
                isCompleted = false,
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
        HomeScreen(
            tasks = tasks,
            {},
            {}
        )
    }

}