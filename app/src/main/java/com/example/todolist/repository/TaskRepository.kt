package com.example.task.repository

import com.example.todolist.data.Task
import com.example.todolist.data.TaskDao


class TaskRepository(private val taskDao: TaskDao) {
    
    fun addTask(newTask: Task) {
        taskDao.addTask(newTask)
    }
    fun updateTaskDetails(newTask: Task) {
        taskDao.updateTask(newTask)
    }
    fun getAllTasks() : List<Task> {
        return  taskDao.getAllTasks()
    }
    fun getTaskById(taskId: Long): Task? {
        return taskDao.findTaskById(taskId)
    }
    fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}