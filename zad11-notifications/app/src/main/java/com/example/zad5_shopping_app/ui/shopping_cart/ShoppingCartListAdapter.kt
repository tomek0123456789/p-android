package com.example.zad5_shopping_app.ui.shopping_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zad5_shopping_app.data.ShoppingCartItem
import com.example.zad5_shopping_app.databinding.FragmentShoppingCartListItemBinding

class ShoppingCartListAdapter(
    private val shoppingCartViewModel: ShoppingCartViewModel
) : ListAdapter<ShoppingCartItem, ShoppingCartListAdapter.ShoppingCartItemViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ShoppingCartItem>() {
            override fun areItemsTheSame(
                oldItem: ShoppingCartItem,
                newItem: ShoppingCartItem,
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ShoppingCartItem,
                newItem: ShoppingCartItem,
            ): Boolean {
                return oldItem.product == newItem.product && oldItem.quantity == newItem.quantity
            }
        }
    }

    inner class ShoppingCartItemViewHolder(
        val binding: FragmentShoppingCartListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingCartItem: ShoppingCartItem) {
            binding.shoppingCartItem = shoppingCartItem
            binding.shoppingCartViewModel = shoppingCartViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartItemViewHolder {
        val view = FragmentShoppingCartListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingCartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingCartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateTextView() {

    }
}