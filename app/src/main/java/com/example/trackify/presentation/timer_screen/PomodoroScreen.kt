package com.example.trackify.presentation.timer_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trackify.R
import com.example.trackify.domain.model.Task
import com.example.trackify.presentation.fontMontserrat
import com.example.trackify.presentation.taskTextStyle
import com.example.trackify.ui.theme.TimerDarkColor
import com.example.trackify.ui.theme.TimerDarkPurple
import com.example.trackify.ui.theme.TimerGrayColor
import com.example.trackify.ui.theme.TimerLightDarkBlue
import com.example.trackify.ui.theme.TimerLinearGradient
import kotlinx.coroutines.delay
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(
    task: Task,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    var totalTime by remember {
        mutableLongStateOf(0)
    }

    var timeLeft by remember {
        mutableLongStateOf(0)
        //mutableLongStateOf(task.getDuration())
    }

    if (task.title.isNotEmpty() && totalTime ==0L){
        totalTime = task.getDuration()
        timeLeft = task.getDuration()
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        while (timeLeft > 0 && !isPaused) {
            delay(1000L)
            timeLeft--
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TimerDarkColor
                ),
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    }
                },
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(TimerDarkColor)
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .border(shape = CircleShape, brush = TimerLinearGradient, width = 2.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Learn Jetpack Compose",
                    style = taskTextStyle,
                    color = TimerGrayColor
                )
            }
            Spacer(modifier = Modifier.height(80.dp))

            val calcProgress = 100f - ((timeLeft.toFloat() / totalTime.toFloat()) * 100f)
            DrawCircularProgress(
                text = task.getFormattedDuration(timeLeft),
                progress = calcProgress
            )

            Spacer(modifier = Modifier.height(80.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(40.dp, 80.dp)
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        isPaused = !isPaused
                    }) {
                        Icon(
                            painter = if(isPaused)painterResource(id = R.drawable.play) else painterResource(id = R.drawable.pause),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.drawBehind {
                                drawCircle(color = TimerLightDarkBlue, radius = 20.dp.toPx())
                            }
                        )
                    }
                    Text(text = if(isPaused) "Play" else "Pause", color = TimerGrayColor)
                }

                Spacer(modifier = Modifier.width(60.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        timeLeft= totalTime
                        isPaused = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.stop),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.drawBehind {
                                drawCircle(color = TimerLightDarkBlue, radius = 20.dp.toPx())
                            }
                        )
                    }
                    Text(text = "Stop", color = TimerGrayColor)
                }
            }
        }
    }

}

@Composable
private fun DrawCircularProgress(
    text:String,
    progress:Float = 10f,
    modifier: Modifier = Modifier
) {

    val progressMax:Float = 100f

    val textMeasure = rememberTextMeasurer()
    val style = TextStyle(
        fontSize = 40.sp,
        color = Color.White,
        fontFamily = fontMontserrat,
        fontWeight = FontWeight.ExtraBold
    )
    //val textToDraw = stringResource(R.string._32_10)
    val textToDraw = text
    val textLayoutResult = remember(textToDraw) {
        textMeasure.measure(textToDraw, style)
    }
    Canvas(modifier = modifier.size(220.dp)) {

        drawCircle(
            color = TimerDarkPurple,
            style = Stroke(
                width = 16.dp.toPx()
            )
        )
        drawArc(
            brush = TimerLinearGradient,
            startAngle = -90f,
            sweepAngle = (progress/progressMax)*360f,
            useCenter = false,
            style = Stroke(16.dp.toPx(), cap = StrokeCap.Round)
        )
        drawText(
            textMeasurer = textMeasure, text = textToDraw,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width / 2,
                y = center.y - textLayoutResult.size.height / 3,
            )
        )
    }
}


@Preview
@Composable
private fun PomodoroScreenPreview() {
    PomodoroScreen(
        task = Task(
            id = 1,
            title = "Learn Kotlin",
            isCompleted = false,
            startTime = LocalTime.of(0,3,0),
            endTime = LocalTime.of(0,5,0),
            reminder = true,
            category = "",
            priority = 0

        )
        , onBack = { /*TODO*/ })


}