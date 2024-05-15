package com.example.todolist.data

import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun findTaskById(taskId: Long): Task

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}