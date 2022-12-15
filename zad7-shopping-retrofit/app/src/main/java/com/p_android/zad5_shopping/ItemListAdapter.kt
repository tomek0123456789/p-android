package com.p_android.zad5_shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.p_android.zad5_shopping.data.Product
import com.p_android.zad5_shopping.data.ProductObject
import com.p_android.zad5_shopping.databinding.FragmentListItemBinding

class ItemListAdapter(
    var productList: List<ProductObject>,
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
        val product = productList[position]
        holder.binding.product = product
        holder.binding.root.setOnClickListener {
            recyclerViewInterface.onItemClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    inner class ItemViewHolder(val binding: FragmentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
}