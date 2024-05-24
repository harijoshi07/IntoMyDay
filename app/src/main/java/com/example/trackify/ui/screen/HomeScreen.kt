package com.example.trackify.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackify.R
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.ui.components.EmptyScreenComponent
import com.example.trackify.ui.components.InfoComponent
import com.example.trackify.ui.components.TaskComponent
import com.example.trackify.ui.components.TaskViewModel
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.TrackifyTheme

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel,
    onAddEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {


    //val tasks by taskViewModel.taskList.collectAsStateWithLifecycle(initialValue = emptyList())
    val tasks by taskViewModel.taskList.collectAsState()
    val totalTasks = tasks.size
    val completedTasks = tasks.count { it.isCompleted }


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
                desc = "$completedTasks/$totalTasks",
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


        if (tasks.isEmpty()) {
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
                items(items = tasks, key = { it.id }) {
                    TaskComponent(
                        task = it,
                        onUpdate = onAddEdit
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
    }

}