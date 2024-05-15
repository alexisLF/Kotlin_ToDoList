package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity (
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0,
    @ColumnInfo(name = "tache_nom")
    var name: String,
    var description: String,
    var isDone: Boolean,

)