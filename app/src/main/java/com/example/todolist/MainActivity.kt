package com.example.todolist

import AddTaskScreen
import ShowTasksScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.model.TaskEntity
import com.example.todolist.viewmodel.TasksViewModel

class MainActivity : ComponentActivity() {
    private val tasksViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(tasksViewModel)
        }
    }
}

@Composable
fun MyApp(tasksViewModel: TasksViewModel) {
    val navController = rememberNavController()
    val tasks by tasksViewModel.readAllData.observeAsState(emptyList())
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            ShowTasksScreen(
                tasks, { navController.navigate("add") },
                onCheckedTask = { task, checked ->
                    task.isDone = checked
                    tasksViewModel.updateTask(task)
                },
            ) {
                tasksViewModel.removeTask(it)
            }
        }
        composable("add") {
            AddTaskScreen({ navController.navigate("home") }) { task: TaskEntity ->
                tasksViewModel.addTask(task)
            }
        }
    }
}