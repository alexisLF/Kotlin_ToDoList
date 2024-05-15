package com.example.todolist.data

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.task.repository.TaskRepository
import kotlinx.coroutines.launch


class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "db-foos"
    ).build()

    private val repo: TaskRepository = TaskRepository(db.taskDao())

    var tasks by mutableStateOf(listOf<Task>())
        private set

    // Load initial data from Room asynchronously.
    init {
        viewModelScope.launch {
            val items = db.taskDao().getAllTasks()
            viewModelScope.launch { tasks = items }
        }

    }

    fun addTask(title:String, desc: String) {
        val taskObj = Task(title = title, description = desc)
        tasks = tasks + listOf(taskObj)
        viewModelScope.launch { repo.addTask(taskObj) }
    }

    fun removeTask(task: Task) {
        tasks = tasks - listOf(task)
        viewModelScope.launch { repo.deleteTask(task) }
    }

    fun changeTaskChecked(item: Task, checked: Boolean) =
        tasks.find { it == item }?.let { task ->
            task.isDone = checked
            task.checked = checked
        }

    fun getAll() {
        viewModelScope.launch {
            repo.getAllTasks()
        }
    }

}