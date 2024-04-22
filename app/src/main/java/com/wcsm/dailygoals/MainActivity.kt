package com.wcsm.dailygoals

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.wcsm.dailygoals.data.model.Task
import com.wcsm.dailygoals.ui.screens.MainScreen
import com.wcsm.dailygoals.ui.theme.DailyGoalsTheme
import com.wcsm.dailygoals.ui.viewmodel.TaskViewModel
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private lateinit var tasksViewModel: TaskViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyGoalsTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    tasksViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

                     val tasksList = listOf(
                        Task(
                            0, "Caminhada mínima de 1Km", 1,
                            R.drawable.ic_directions_run_24,0
                        ),
                        Task(
                            1, "Estudar Inglês", 1,
                            R.drawable.ic_language_us_24, 0
                        ),
                        Task(
                            2, "Exercícios Físicos", 1,
                            R.drawable.ic_fitness_center_24, 0
                        ),
                        Task(
                            3, "Estudar Programação", 2,
                            R.drawable.ic_code_24, 0
                        ),
                        Task(
                            4, "Foco Total", 0,
                            R.drawable.ic_check_circle_24,0
                        )
                    )

                    val prefs = getSharedPreferences("app_pref", Context.MODE_PRIVATE)
                    val tasksCreated = prefs.getBoolean("tasks_created", false)

                    if(!tasksCreated) {
                        tasksViewModel.insertAllTasks(tasksList)
                        prefs.edit().putBoolean("tasks_created", true).apply()
                        scheduleAlarm()
                    }

                    MainScreen(tasksViewModel)
                }
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun scheduleAlarm() {
        val intent = Intent(applicationContext, ResetTasksReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // FOR TEST
        /*
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 21)
        calendar.set(Calendar.MINUTE, 58)
        */

        // FOR PRODUCTION
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            // Adiciona um dia para a próxima ocorrência
            add(Calendar.DAY_OF_YEAR, 1)
        }

        val currentTimeMillis = calendar.timeInMillis

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            currentTimeMillis,
            pendingIntent
        )
    }
}
