package com.wcsm.dailygoals.data.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.wcsm.dailygoals.data.model.Task

class TaskDao(context: Context): ITaskDao {

    private val writing = DatabaseHelper(context).writableDatabase
    private val reading = DatabaseHelper(context).readableDatabase

    override fun insertTasks(tasks: List<Task>): Boolean {
        val insertErrors = mutableListOf<String>()

        tasks.forEach {
            val content = ContentValues()
            content.put(DatabaseHelper.COL_TASK_TITLE, it.title)
            content.put(DatabaseHelper.COL_TASK_MIN_DURATION, it.minDuration)
            content.put(DatabaseHelper.COL_TASK_ICON_ID, it.iconId)
            content.put(DatabaseHelper.COL_TASK_COMPLETED, it.completed)

            try {
                writing.insert(
                    DatabaseHelper.TABLE_NAME,
                    null,
                    content
                )
                Log.i("info_db", "TASK saved successfully")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("info_db", "Error saving TASK")
                insertErrors.add("$it.title")
            }
        }

        return insertErrors.isEmpty()
    }

    override fun getAll(): List<Task> {
        val taskList = mutableListOf<Task>()

        val sql = "SELECT" +
                " ${DatabaseHelper.COL_TASK_ID}," +
                " ${DatabaseHelper.COL_TASK_TITLE}," +
                " ${DatabaseHelper.COL_TASK_MIN_DURATION}," +
                " ${DatabaseHelper.COL_TASK_ICON_ID}," +
                " ${DatabaseHelper.COL_TASK_COMPLETED}" +
                " FROM ${DatabaseHelper.TABLE_NAME};"


        val cursor = reading.rawQuery(sql, null)

        cursor.use {
            val idIndex = it.getColumnIndex(DatabaseHelper.COL_TASK_ID)
            val titleIndex = it.getColumnIndex(DatabaseHelper.COL_TASK_TITLE)
            val minDurationIndex = it.getColumnIndex(DatabaseHelper.COL_TASK_MIN_DURATION)
            val iconIdIndex = it.getColumnIndex(DatabaseHelper.COL_TASK_ICON_ID)
            val completedIndex = it.getColumnIndex(DatabaseHelper.COL_TASK_COMPLETED)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val title = cursor.getString(titleIndex)
                val minDuration = cursor.getInt(minDurationIndex)
                val iconId = cursor.getInt(iconIdIndex)
                val completed = cursor.getInt(completedIndex)

                taskList.add(
                    Task(id, title, minDuration, iconId, completed)
                )
            }

            return taskList
        }
    }

    override fun updateStatus(taskId: Int, completed: Int): Boolean {
        val args = arrayOf(taskId.toString())
        val content = ContentValues()
        content.put(DatabaseHelper.COL_TASK_COMPLETED, completed)

        try {
            writing.update(
                DatabaseHelper.TABLE_NAME,
                content,
                "${DatabaseHelper.COL_TASK_ID} = ?",
                args
            )
            Log.i("info_db", "TASK updated successfully")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Error updating TASK")
            return false
        }

        return true
    }

    override fun resetAllStatus(): Boolean {
        val updateErrors = mutableListOf<String>()

        val tasksList = getAll()

        if(tasksList.isNotEmpty()) {
            tasksList.forEach {
                try {
                    updateStatus(it.uid, 0)
                    Log.i("info_db", "TASK saved successfully")
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.i("info_db", "Error saving TASK")
                    updateErrors.add(it.title)
                }
            }
        }

        return updateErrors.isEmpty()
    }
}