package com.p_android.zad4_todolist

object TaskObject {
    val TASKS: MutableList<Task> = ArrayList()
    private val TASK_MAP: MutableMap<Int, Task> = HashMap()

    init {
        for (i in 1..10) {
            addTask(createPlaceholderTask(i))
        }
    }

    private fun addTask(task: Task) {
        TASKS.add(task)
        TASK_MAP[task.id] = task
    }

    private fun createPlaceholderTask(id: Int): Task {
        return Task(id, "Task no. $id", makeDetails(id))
    }

    private fun makeDetails(id: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(id)
        for (i in 0 until id) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    data class Task(val id: Int, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
