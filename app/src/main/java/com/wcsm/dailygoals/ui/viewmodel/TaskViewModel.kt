package com.wcsm.dailygoals.ui.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.wcsm.dailygoals.data.database.TaskDao
import com.wcsm.dailygoals.data.model.Task

class TaskViewModel(private val application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDao(application.applicationContext)

    var sequenceOfDays: Int
        get() {
            val prefs = application.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
            return prefs.getInt("sequence_of_days", 0)
        }
        set(value) {
            val prefs = application.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
            prefs.edit().putInt("sequence_of_days", value).apply()
        }

    fun insertAllTasks(tasksList: List<Task>) {
        if(taskDao.insertTasks(tasksList)) {
            showToastMessage("Todas as tarefas foram inseridas.")
        } else {
            showToastMessage("ERRO ao inserir todas as tarefas.")
        }
    }

    fun getAllTasks(): List<Task> {
        return taskDao.getAll()
    }

    fun updateTaskStatus(taskId: Int, completed: Int) {
        if(taskDao.updateStatus(taskId, completed)) {
            showToastMessage("Tarefa completa!")
        } else {
            showToastMessage("Tarefa incompleta.")
        }
    }

    fun resetAllTasksStatus() {
        if(taskDao.resetAllStatus()) {
            showToastMessage("Tarefas resetadas!")
        } else {
            showToastMessage("ERRO ao resetar tarefas.")
        }
    }

    fun incrementSequenceOfDays() {
        val tasksList = getAllTasks()
        var anyIncompleteTask = false

        // Verify if all tasks are completed
        tasksList.forEach { task ->
            if(task.completed == 0) {
                anyIncompleteTask = true
            }
        }

        // If all tasks are completed increment days streak
        if(!anyIncompleteTask) {
            if(sequenceOfDays < 30) {
                sequenceOfDays++
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(application.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
