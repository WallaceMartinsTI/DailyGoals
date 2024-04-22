package com.wcsm.dailygoals.data.database

import com.wcsm.dailygoals.data.model.Task

interface ITaskDao {
    fun insertTasks(tasks: List<Task>): Boolean
    fun getAll(): List<Task>
    fun updateStatus(taskId: Int, completed: Int): Boolean
    fun resetAllStatus(): Boolean
}