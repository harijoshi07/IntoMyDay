package com.example.trackify.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.example.trackify.R
import com.example.trackify.presentation.h1TextStyle
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.presentation.taskDescTextStyle
import com.example.trackify.presentation.taskTextStyle
import com.example.trackify.ui.theme.Blue200
import com.example.trackify.ui.theme.Blue500
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.Red
import com.example.trackify.ui.theme.TrackifyTheme
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(modifier: Modifier = Modifier) {

    //TODO: remove after testing
    val taskId: Int? = null

    val context = LocalContext.current

    val appBarTitle = if (taskId == null) {
        stringResource(R.string.add_task)
    } else stringResource(R.string.edit_task)


    var taskTitle by remember {
        mutableStateOf("")
    }

    var taskStartTime by remember {
        mutableLongStateOf(0)
    }
    var taskEndTime by remember {
        mutableLongStateOf(0)
    }

    var isTaskReminderOn by remember {
        mutableStateOf(true)
    }


    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.padding(8.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ), title = {
                Text(text = appBarTitle, style = h1TextStyle)
            }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
                }
            },
            actions = {
                if (taskId != null) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "")
                    }
                }
            }
        )
    }) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
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
                        taskTitle = it
                    },
                    textStyle = h2TextStyle,
                    placeholder = {
                        Text(text = stringResource(R.string.what_would_you_like_to_do))
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        focusedTextColor = MaterialTheme.colorScheme.secondary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .focusRequester(FocusRequester())
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
                            taskStartTime = it.toNanoOfDay()
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
                            taskEndTime = it.toNanoOfDay()
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
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(
                                text = "Add Reminder",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        TextButton(
                            onClick = {
                                if (taskTitle.isNotEmpty()) {
                                    //taskViewModel.addTask(taskTitle, taskStartTime, taskEndTime, isTaskReminderOn)
                                    taskTitle = ""
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
                                text = appBarTitle,
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

@Preview
@Composable
private fun AddEditScreenPreview() {
    TrackifyTheme(
        darkTheme = true, dynamicColor = false
    ) {
        AddEditScreen()
    }

}