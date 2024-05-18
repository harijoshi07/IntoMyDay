package com.example.trackify.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.R
import com.example.trackify.ui.components.InfoComponent
import com.example.trackify.ui.theme.Blue
import com.example.trackify.ui.theme.Green

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
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

}

@Preview
@Composable
private fun PrevHomeScreen() {
    HomeScreen()
}