package com.example.trackify.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackify.R
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.ui.components.EmptyScreenComponent
import com.example.trackify.ui.components.InfoComponent
import com.example.trackify.ui.components.TaskViewModel
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.TrackifyTheme

@Composable
fun HomeScreen(viewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val tasks = viewModel.taskList.collectAsState().value

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
                desc = "3/6",
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
        }

        //EmptyScreenComponent()
    }

}

@Preview
@Composable
private fun PrevHomeScreen() {
    TrackifyTheme(darkTheme = true, dynamicColor = false) {
        // HomeScreen()
    }

}