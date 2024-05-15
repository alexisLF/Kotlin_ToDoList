package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.task.repository.TaskRepository
import com.example.todolist.data.AppDatabase
import com.example.todolist.model.TaskEntity
import kotlinx.coroutines.launch


class TasksViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<TaskEntity>>
    private val repository: TaskRepository

    // init block is executed FIRST when ViewModel is created
    // Load initial data from Room asynchronously.
    init {
        val taskDao = AppDatabase.getInstance(application).taskDao()
        //    initializing repository and passing dao
        repository = TaskRepository(taskDao)
        //    get all data from repository in the variable readAllData
        readAllData = repository.readAllData
    }

    fun addTask(task: TaskEntity) {
        // viewModelScope is part of coroutines
        viewModelScope.launch{
            repository.addTask(task)
        }
    }

    fun removeTask(task: TaskEntity) {
        // viewModelScope is part of coroutines
        viewModelScope.launch{
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: TaskEntity){
        //    viewModelScope is part of coroutines
        viewModelScope.launch{
            repository.updateTask(task)
        }
    }

}