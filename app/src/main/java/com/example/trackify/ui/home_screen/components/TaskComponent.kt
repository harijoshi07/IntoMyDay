package com.example.trackify.ui.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.R
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.taskDescTextStyle
import com.example.trackify.presentation.taskTextStyle
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.LightGray
import com.example.trackify.ui.theme.Red
import com.example.trackify.ui.theme.Yellow
import java.time.LocalTime

@Composable
fun TaskComponent(
    task: Task,
    onUpdate: (Int) -> Unit,
    onComplete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val priorityColors = listOf(LightGray, Yellow, Red)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = priorityColors[task.priority], shape = RoundedCornerShape(8.dp))
            .padding(start = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(32.dp)
                ) {
                    if (task.isCompleted == true) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check_circle),
                            contentDescription = null,
                            tint = Green,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        //to create a toggle circle
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .border(width = 2.dp, color = LightGray, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) { }

                    }

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = task.title, style = taskTextStyle, color = Color.White)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = LightGray
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = task.getFormattedTime(),
                                style = taskDescTextStyle,
                                color = LightGray
                            )
                        }

                    }


                    IconButton(onClick = { onUpdate(task.id) }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = LightGray
                        )

                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun TaskComponentPreview() {
    TaskComponent(
        task = Task(
            id = 0,
            title = "Learn Compose",
            isCompleted = false,
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            reminder = true,
            category = "others"
        ),
        onUpdate = {},
        onComplete = {}
    )
}