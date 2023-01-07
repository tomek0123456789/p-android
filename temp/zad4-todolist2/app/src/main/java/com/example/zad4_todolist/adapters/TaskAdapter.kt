package com.example.zad4_todolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.zad4_todolist.placeholder.PlaceholderContent.PlaceholderItem
import com.example.zad4_todolist.databinding.FragmentTaskBinding
import com.example.zad4_todolist.models.Task
import com.example.zad4_todolist.models.TaskViewModel

class TaskAdapter(
    private val values: List<Task>,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    //todo db get all tasks
    private val tasks = mutableListOf(
        Task(1, "a", "2"),
        Task(2, "a", "2"),
        Task(3, "a", "2"),
        Task(4, "a", "2"),
        Task(5, "a", "2"),
        Task(6, "a", "2"),
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        return TaskViewHolder(FragmentTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.task = values[position]
    }

    override fun getItemCount(): Int = values.size

    inner class TaskViewHolder(
        val binding: FragmentTaskBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    fun delete(position: Int) {
        //todo db remove
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

}