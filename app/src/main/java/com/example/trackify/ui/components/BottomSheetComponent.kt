package com.example.trackify.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.fontRoboto
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.presentation.taskTextStyle
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Blue200
import com.example.trackify.ui.theme.Blue500
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.Red
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.time.LocalTime
import kotlin.coroutines.coroutineContext
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComponent(
    scope:CoroutineScope,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {


    val context = LocalContext.current
    val focusRequester = FocusRequester()

    var taskTitle by remember {
        mutableStateOf("")
    }
    var taskStartTime by remember {
        mutableLongStateOf(0)
    }
    var taskEndTime by remember {
        mutableLongStateOf(0)
    }

    val task = Task(
        id = 0,
        title = taskTitle,
        false,
        startTime = taskStartTime,
        endTime = taskEndTime
    )

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            containerColor = Blue500,
            contentColor = Color.White,
            onDismissRequest = {
                onDismiss()
                taskTitle = ""
            },
            sheetState = sheetState
        ) {
            LaunchedEffect(
                key1 = true,
                block = {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "What would you like to do?",
                    style = h2TextStyle
                )

                TextField(
                    value = taskTitle,
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Blue200,
                        cursorColor = Color.White
                    ),
                    textStyle = TextStyle.Default.copy(fontFamily = fontRoboto),
                    onValueChange = { taskTitle = it },
                    placeholder = { Text(text = "e.g. Water the Plants") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Start time",
                            style = taskTextStyle,
                            color = Green
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        WheelTimePicker(
                            timeFormat = TimeFormat.AM_PM,
                            minTime = LocalTime.now(),
                            startTime = LocalTime.now()
                        ) {
                            taskStartTime = it.toNanoOfDay()
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "End time",
                            style = taskTextStyle,
                            color = Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        WheelTimePicker(
                            timeFormat = TimeFormat.AM_PM,
                            minTime = LocalTime.now().plusMinutes(5),
                            startTime = LocalTime.now().plusHours(1)
                        ) {
                            taskEndTime = it.toNanoOfDay()
                        }
                    }

                }

                Button(
                    onClick = {
                        if (taskTitle.isNotEmpty() && taskStartTime < taskEndTime) {
                            taskViewModel.insertTask(task)
                            taskTitle = ""

                            scope.launch {
                                sheetState.hide()
                            }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        onDismiss()
                                    }
                                }
                        } else {
                            Toast.makeText(
                                context,
                                "Task Empty!!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue200,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Add Task")
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}


@Preview
@Composable
private fun BottomSheetComponentPreview() {
    // BottomSheetComponent()

}