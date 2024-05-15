package com.example.todolist.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Task(val title: String, val description: String, var isDone: Boolean = false) {
    var checked: Boolean by mutableStateOf(isDone)
}
