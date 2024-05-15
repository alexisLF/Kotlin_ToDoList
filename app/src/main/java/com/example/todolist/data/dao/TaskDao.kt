package com.example.todolist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.model.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun findTaskById(taskId: Long): TaskEntity

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<TaskEntity>>

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}