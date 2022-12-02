package com.p_android.zad5_shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.p_android.zad5_shopping.databinding.FragmentListItemBinding

class ItemListAdapter(
    val itemList: List<Item>,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: FragmentListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.fragment_list_item,
            parent,
            false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.item = item
        holder.binding.root.setOnClickListener {
            recyclerViewInterface.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder(val binding: FragmentListItemBinding) : RecyclerView.ViewHolder(binding.root)
}