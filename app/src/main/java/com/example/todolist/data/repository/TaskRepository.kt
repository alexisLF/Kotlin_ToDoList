package com.example.task.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.dao.TaskDao
import com.example.todolist.model.TaskEntity


class TaskRepository(private val taskDao: TaskDao) {
    val readAllData: LiveData<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addTask(newTask: TaskEntity) {
        taskDao.addTask(newTask)
    }
    suspend fun updateTask(newTask: TaskEntity) {
        taskDao.updateTask(newTask)
    }
    fun getTaskById(taskId: Long): TaskEntity? {
        return taskDao.findTaskById(taskId)
    }
    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}