package com.example.trackify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.fontRoboto
import com.example.trackify.presentation.h1TextStyle
import com.example.trackify.ui.home_screen.HomeScreen
import com.example.trackify.ui.home_screen.HomeScreenEvent
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.TrackifyTheme
import com.example.trackify.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackifyApp(
    tasks: List<Task>,
    onEvent: (HomeScreenEvent) -> Unit,
    onAddTask: () -> Unit,
    onEditTask: (id: Int) -> Unit,
    onClickCompletedInfo: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.padding(end = 8.dp),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = h1TextStyle
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = null,
                        tint = Yellow,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "12", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = fontRoboto
                        )
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddTask()

                },
                containerColor = Blue,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        /*bottomBar = {
            BottomSheetComponent(
                scope = scope,
                isBottomSheetVisible,
                sheetState = sheetState,
                onDismiss = { isBottomSheetVisible = false },
                taskViewModel = taskViewModel
            )
        }*/
    ) {

        Column(modifier = Modifier.padding(it)) {
            HomeScreen(
                tasks,
                onEvent,
                onEditTask,
                onClickCompletedInfo
            )
        }

    }

}

@Preview
@Composable
private fun PrevTrackifyApp() {
    TrackifyTheme(darkTheme = true, dynamicColor = false) {
        //TrackifyApp()
    }


}