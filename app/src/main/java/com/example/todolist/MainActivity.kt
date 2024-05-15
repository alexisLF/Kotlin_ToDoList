package com.example.todolist

import AddTask
import ShowTasks
import com.example.todolist.data.Task
import com.example.todolist.data.TaskList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.data.TasksViewModel

class MainActivity : ComponentActivity() {
    private val notesViewModel by viewModels<TasksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(notesViewModel)
        }
    }
}

fun main() {
    val taskList = TaskList()

    // Ajout de quelques tâches à la liste
    taskList.addTask(Task("Faire les courses", "Acheter des fruits et légumes"))
    taskList.addTask(Task("Réunion de travail", "Préparer la présentation"))
    taskList.addTask(Task("Faire du sport", "30 minutes de jogging"))

    // Affichage initial des tâches
    println("Avant mise à jour:")
    taskList.displayTasks()

    // Marquer la première tâche comme terminée
    taskList.markTaskAsDone(0)

    // Affichage après la mise à jour
    println("\nAprès mise à jour:")
    taskList.displayTasks()
}

@Composable
fun MyApp(notesViewModel: TasksViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen({ navController.navigate("add") }, notesViewModel) }
        composable("add") { AddScreen({ navController.navigate("home") }, notesViewModel) }
    }
}

@Composable
fun HomeScreen(onNavigate: () -> Unit, notesViewModel: TasksViewModel) {
    ShowTasks(notesViewModel.tasks, onNavigate, onCheckedTask = { task, checked ->
        notesViewModel.changeTaskChecked(task, checked)
    },) {
        notesViewModel.removeTask(it)
    }
}

@Composable
fun AddScreen(onNavigate: () -> Unit, notesViewModel:TasksViewModel) {
    AddTask(onNavigate) { title: String, desc: String ->
        notesViewModel.addTask(title, desc)
    }
}