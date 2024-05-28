package com.example.trackify.ui.add_edit_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackify.domain.model.Reminder
import com.example.trackify.presentation.compTextStyle
import com.example.trackify.presentation.h1TextStyle
import com.example.trackify.presentation.h2TextStyle
import com.example.trackify.ui.theme.Blue200
import com.example.trackify.ui.theme.Blue500
import com.example.trackify.ui.theme.Green
import com.example.trackify.ui.theme.LightGray
import com.example.trackify.ui.theme.Red
import com.example.trackify.ui.theme.TrackifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderBottomSheet(modifier: Modifier = Modifier) {

    val availableReminder = remember {
        mutableStateListOf(
            Reminder(5),
            Reminder(10),
            Reminder(15),
            Reminder(30)
        )
    }

    ElevatedCard(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Add Reminder",
                style = h2TextStyle,
                color = LightGray
            )
            Spacer(modifier = Modifier.height(12.dp))
            for (reminder in availableReminder) {
                ReminderCheckBox(reminder.time, reminder.isTurnedOn)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                //.padding(horizontal = 12.dp)
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue200.copy(alpha = 0.3f)
                    ),
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Text(
                        text = "Cancel", style = compTextStyle
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    )
                ) {
                    Text(
                        text = "Done",
                        style = compTextStyle,
                        color = Color.Black
                    )
                }
            }

        }
    }


}


@Composable
fun ReminderCheckBox(time: Int, isTurnedOn: Boolean, modifier: Modifier = Modifier) {

    var isChecked by remember {
        mutableStateOf(isTurnedOn)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(color = Blue200, shape = RoundedCornerShape(8.dp))
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier
                .padding(12.dp, 6.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Green,
                uncheckedColor = Color.Gray
            )
        )
        Text(
            text = "$time mins before",
            style = compTextStyle,
            color = LightGray,
            modifier = Modifier.padding(12.dp)
        )

    }

}

@Preview
@Composable
private fun AddReminderBottomSheetPreview() {
//    TrackifyTheme(
//        darkTheme = true,
//        dynamicColor = false
//    ) {
//        AddReminderBottomSheet()
//    }
    AddReminderBottomSheet()
    //ReminderCheckBox(5,false)

}