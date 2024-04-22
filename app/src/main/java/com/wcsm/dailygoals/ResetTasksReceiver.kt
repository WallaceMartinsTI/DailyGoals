package com.wcsm.dailygoals

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.wcsm.dailygoals.ui.viewmodel.TaskViewModel
import java.util.Calendar

class ResetTasksReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val taskViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application).create(
            TaskViewModel::class.java)

        taskViewModel.incrementSequenceOfDays()

        taskViewModel.resetAllTasksStatus()

        scheduleNextAlarm(context)
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun scheduleNextAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ResetTasksReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // FOR TESTS
        /*
        val calendar = Calendar.getInstance().apply {
            //set(Calendar.HOUR_OF_DAY, 14)
            //set(Calendar.MINUTE, 30)
            // Add passed minutes to next run
            add(Calendar.MINUTE, 2)
        }
        */

        // FOR PRODUCTION
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            // Add a day to next run
            add(Calendar.DAY_OF_YEAR, 1)
        }

        val nextTimeMillis = calendar.timeInMillis

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextTimeMillis,
            pendingIntent
        )
    }
}