package com.example.zad4_todolist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zad4_todolist.databinding.FragmentTaskBinding
import com.example.zad4_todolist.db.SqliteDatabase
import com.example.zad4_todolist.models.Task

class TaskAdapter(context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val db = SqliteDatabase(context)
    private val tasks = db.getTasks()
    //todo db get all tasks
//    private val tasks = mutableListOf(
//        Task(1, "a", "2"),
//        Task(2, "a", "2"),
//        Task(3, "a", "2"),
//        Task(4, "a", "2"),
//        Task(5, "a", "2"),
//        Task(6, "a", "2"),
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(FragmentTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.task = tasks[position]

    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(
        val binding: FragmentTaskBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    fun delete(position: Int) {
        db.deleteTask(position)
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(title: String) {
        val id = db.addTask(title)
        tasks.add(Task(id.toInt(), title, ""))
        notifyItemInserted(tasks.size)
    }

}