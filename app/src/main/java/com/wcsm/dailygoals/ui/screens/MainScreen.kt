package com.wcsm.dailygoals.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wcsm.dailygoals.ui.screens.components.HeaderCounter
import com.wcsm.dailygoals.ui.screens.components.MotivationalQuotes
import com.wcsm.dailygoals.ui.screens.components.TaskList
import com.wcsm.dailygoals.ui.theme.DailyGoalsTheme
import com.wcsm.dailygoals.ui.viewmodel.TaskViewModel

@Composable
fun MainScreen(taskViewModel: TaskViewModel) {

    val taskList by remember { mutableStateOf(taskViewModel.getAllTasks()) }
    val daysStreak by remember { mutableIntStateOf(taskViewModel.sequenceOfDays) }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF6FCFF)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HeaderCounter(sequenceOfDays = daysStreak)//, modifier = Modifier.padding(vertical = 16.dp))

        Divider(
            modifier = Modifier
                .width(360.dp)
                .padding(vertical = 8.dp),
            color = Color.Black,
            thickness = 1.dp
        )

        if(taskList.isNotEmpty()) {
            TaskList(tasks = taskList, taskViewModel)
        } else {
            Text(
                "A lista de tarefas está vazia.",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Divider(
            modifier = Modifier
                .width(360.dp)
                .padding(vertical = 8.dp),
            color = Color.Black,
            thickness = 1.dp
        )

        MotivationalQuotes()
    }
}
