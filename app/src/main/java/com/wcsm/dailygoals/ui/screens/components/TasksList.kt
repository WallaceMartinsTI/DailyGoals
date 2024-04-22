package com.wcsm.dailygoals.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wcsm.dailygoals.data.model.Task
import com.wcsm.dailygoals.ui.viewmodel.TaskViewModel

@Composable
fun TaskList(
    tasks: List<Task>?,
    taskViewModel: TaskViewModel
) {
    var taskList by remember { mutableStateOf(tasks) }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        if(!tasks.isNullOrEmpty()) {
            items (tasks) { task ->
                TaskListItem(
                    taskId = task.uid,
                    taskTitle = task.title,
                    checked = task.completed,
                    minDuration = task.minDuration,
                    iconId = task.iconId,
                    onCheckedChange = { taskId, newCompletedStatus ->
                        taskViewModel.updateTaskStatus(taskId, newCompletedStatus)
                        taskList = taskViewModel.getAllTasks()
                    }
                )
            }
        }
    }
}