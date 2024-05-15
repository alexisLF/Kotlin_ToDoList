package com.example.todolist.data

class TaskList {
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun markTaskAsDone(taskIndex: Int) {
        if (taskIndex in 0 until tasks.size) {
            tasks[taskIndex].isDone = true
        } else {
            println("Index de tâche invalide.")
        }
    }

    fun displayTasks() {
        if (tasks.isEmpty()) {
            println("Aucune tâche dans la liste.")
        } else {
            println("Liste des tâches:")
            for ((index, task) in tasks.withIndex()) {
                val status = if (task.isDone) "Terminée" else "En cours"
                println("$index. ${task.title} - ${task.description} [$status]")
            }
        }
    }
}
