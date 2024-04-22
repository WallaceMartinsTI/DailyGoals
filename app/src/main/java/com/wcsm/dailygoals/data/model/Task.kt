package com.wcsm.dailygoals.data.model

data class Task(
    val uid: Int,
    val title: String,
    val minDuration: Int,
    val iconId: Int,
    var completed: Int = 0
)
