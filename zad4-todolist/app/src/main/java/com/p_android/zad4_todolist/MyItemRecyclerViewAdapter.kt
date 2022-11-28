package com.p_android.zad4_todolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil

import com.p_android.zad4_todolist.databinding.FragmentItemBinding

class MyItemRecyclerViewAdapter(
    private val values: List<TaskObject.Task>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val b = binding

        fun bind(task: TaskObject.Task) {
            b.task = task
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}