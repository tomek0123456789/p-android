package com.example.zad4_todolist.models

import androidx.lifecycle.ViewModel

data class Task(
    val id: Int,
    val title: String,
    val description: String
)

class TaskViewModel : ViewModel() {

}