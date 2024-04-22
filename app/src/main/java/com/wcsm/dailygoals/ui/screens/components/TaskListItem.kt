package com.wcsm.dailygoals.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wcsm.dailygoals.util.manageDurationMessage

@Composable
fun TaskListItem(
    taskId: Int,
    taskTitle: String,
    minDuration: Int,
    iconId: Int,
    checked: Int,
    onCheckedChange: (Int, Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                1.dp,
                Color.Blue,
                RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            var isCheckedTask by rememberSaveable { mutableStateOf(checked != 0) }

            Icon(
                painter = painterResource(iconId),
                contentDescription = "Code Icon",
                modifier = Modifier.padding(4.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = taskTitle,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )

                val durationText = manageDurationMessage(minDuration)
                Text(
                    text = durationText,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
            Checkbox(
                checked = isCheckedTask,
                onCheckedChange = { isChecked ->
                    isCheckedTask = isChecked
                    onCheckedChange(taskId, if(isCheckedTask) 1 else 0)
                }
            )
        }
    }
}
