package com.example.zad5_shopping_app.ui.product_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.activityViewModels
import com.example.zad5_shopping_app.R
import com.example.zad5_shopping_app.data.Product
import com.example.zad5_shopping_app.databinding.FragmentProductDetailsBinding
import com.example.zad5_shopping_app.ui.shopping_cart.ShoppingCartViewModel

class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var product: Product
    private val shoppingCartViewModel: ShoppingCartViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getParcelable(PRODUCT_ARG)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.product = product
        binding.shoppingCartViewModel = shoppingCartViewModel
        binding.productImage.setImageResource(R.drawable.image_ducke_froge)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PRODUCT_ARG = "product"
    }
}