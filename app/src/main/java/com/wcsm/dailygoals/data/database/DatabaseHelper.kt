package com.wcsm.dailygoals.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, VERSION) {
    companion object {
        const val DATABASE = "Tasks.db"
        const val VERSION = 1
        const val TABLE_NAME = "tasks"
        const val COL_TASK_ID = "task_id"
        const val COL_TASK_TITLE = "title"
        const val COL_TASK_MIN_DURATION = "min_duration"
        const val COL_TASK_ICON_ID = "icon_id"
        const val COL_TASK_COMPLETED = "completed"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                " $COL_TASK_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " $COL_TASK_TITLE VARCHAR(34) NOT NULL," +
                " $COL_TASK_MIN_DURATION INTEGER NOT NULL," +
                " $COL_TASK_ICON_ID INTEGER NOT NULL," +
                " $COL_TASK_COMPLETED INTEGER NOT NULL DEFAULT 0" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Created TASKS table successful")
        } catch (e: Exception) {
            Log.i("info_db", "Error creating TASK table")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
