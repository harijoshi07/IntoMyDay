package com.example.trackify.ui.add_edit_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import com.example.trackify.ui.home_screen.TaskViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.example.trackify.R
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.h1TextStyle
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.presentation.taskTextStyle
import com.example.trackify.ui.add_edit_screen.components.ConfirmDeleteDialog
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.Red

import kotlinx.coroutines.job
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: Int,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit
) {
    val taskTitle = taskViewModel.task.title
    val taskStartTime = taskViewModel.task.startTime
    val taskEndTime = taskViewModel.task.endTime

    val context = LocalContext.current
    val focusRequester = FocusRequester()

    var isTaskReminderOn by remember {
        mutableStateOf(true)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }



    LaunchedEffect(key1 = true,
        block = {
            taskViewModel.getTaskById(taskId)
        })

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.padding(8.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title = {
                Text(
                    text = stringResource(R.string.edit_task),
                    style = h1TextStyle
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {

                IconButton(onClick = {
                    showDialog = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            })
    }) { it ->

        LaunchedEffect(key1 = true,
            block = {
                coroutineContext.job.invokeOnCompletion {
                    focusRequester.requestFocus()
                }
            })

        //confirm delete dialog
        if (showDialog) {
            ConfirmDeleteDialog(
                onClose = { showDialog = false },
                onDelete = {
                    taskViewModel.deleteTask(taskViewModel.task)
                    showDialog = false
                    onBack()
                }
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = {
                        taskViewModel.updateTitle(it)
                    },
                    textStyle = h2TextStyle,
                    placeholder = {
                        Text(text = stringResource(R.string.what_would_you_like_to_do))
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        //focusedTextColor = MaterialTheme.colorScheme.secondary,
                        //unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 32.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.start_time),
                            style = taskTextStyle,
                            color = Green
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        WheelTimePicker(
                            startTime = LocalTime.now(),
                            minTime = LocalTime.now(),
                            maxTime = LocalTime.MAX,
                            timeFormat = TimeFormat.AM_PM,
                            textColor = Color.White
                        ) {
                            taskViewModel.updateStartTime(it)
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.end_time),
                            style = taskTextStyle,
                            color = Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        WheelTimePicker(
                            startTime = LocalTime.now().plusHours(1),
                            minTime = LocalTime.now().plusMinutes(5),
                            maxTime = LocalTime.MAX,
                            timeFormat = TimeFormat.AM_PM,
                            textColor = Color.White
                        ) {
                            taskViewModel.updateEndTime(it)
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp, 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.reminder),
                        style = h2TextStyle,
                        color = Color.White
                    )
                    Switch(
                        checked = isTaskReminderOn,
                        onCheckedChange = { isTaskReminderOn = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Green,
                            checkedTrackColor = MaterialTheme.colorScheme.secondary,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }


                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(20.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        TextButton(
                            onClick = {
                                if (taskTitle.isNotEmpty()) {
                                    val task = Task(
                                        0,
                                        taskTitle,
                                        false,
                                        taskStartTime,
                                        taskEndTime,
                                    )
                                    taskViewModel.updateTask(task)
                                    onBack()
                                } else if (taskStartTime >= taskEndTime) {
                                    Toast.makeText(
                                        context,
                                        "Invalid Time",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Title can't be empty",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.update_task),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}