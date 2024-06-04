package com.example.trackify.ui.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.R
import com.example.trackify.presentation.infoDescTextStyle
import com.example.trackify.presentation.infoTextStyle
import com.example.trackify.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoComponent(
    title: String,
    desc: String,
    icon: Int,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = title, style = infoTextStyle)

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = desc, style = infoDescTextStyle)
            }
        }
    }
}

@Preview()
@Composable
private fun PrevInfoComponent() {
    InfoComponent(
        title = "Completed",
        desc = "1/3",
        icon = R.drawable.ic_task_list,
        backgroundColor = Blue,
        onClick = {},
        modifier = Modifier
    )

}