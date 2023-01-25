package com.example.zad5_shopping_app.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zad5_shopping_app.data.Product
import com.example.zad5_shopping_app.databinding.FragmentProductsListItemBinding
import com.example.zad5_shopping_app.ui.shopping_cart.ShoppingCartViewModel

class ProductsListAdapter(
    private val shoppingCartViewModel: ShoppingCartViewModel
) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {

    private val products = listOf(
        Product(1, "a", "aa", 1.0),
        Product(2, "b", "bb", 2.0),
        Product(3, "c", "cc", 3.0),
        Product(4, "d", "dd", 4.0),
        Product(5, "e", "ee", 5.0),
        Product(1000, "f", "ff", 6.0),
        Product(100000, "g", "gg", 69.0),
        Product(100001, "h", "hh", 69.0),
        Product(100002, "i", "ii", 69.0),
        Product(100003, "j", "jj", 69.0),
        Product(100003, "k", "kk", 69.0),
    )
    inner class ProductViewHolder(
        val binding: FragmentProductsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.shoppingCartViewModel = shoppingCartViewModel
            itemView.setOnClickListener {
                val action = ProductsListFragmentDirections.actionNavigationProductsToProductDetailsFragment(product)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = FragmentProductsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.binding.plusButton.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "Added item to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = products.size
}