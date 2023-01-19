package com.example.zad5_shopping_app.ui.shopping_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.zad5_shopping_app.R
import com.example.zad5_shopping_app.data.Product
import com.example.zad5_shopping_app.databinding.FragmentShoppingCartListBinding

class ShoppingCartListFragment : Fragment() {
    private var _binding: FragmentShoppingCartListBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingCartAdapter: ShoppingCartListAdapter
    private lateinit var recyclerView: RecyclerView
    private val shoppingCartViewModel: ShoppingCartViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentShoppingCartListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //todo przekazac lifecycleownera do adaptera i tam ustawic observe na livedata, zeby zmienialo cyferke
        shoppingCartAdapter = ShoppingCartListAdapter(shoppingCartViewModel)
        recyclerView = binding.shoppingCartRecyclerView
        recyclerView.adapter = shoppingCartAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        shoppingCartViewModel.products.observe(viewLifecycleOwner) { cart ->
            cart.let {
                shoppingCartAdapter.submitList(it)
//                shoppingCartAdapter.notifyDataSetChanged()
//                println(getString(R.string.shopping_cart_price, shoppingCartViewModel.price.toString()))
                binding.priceView.text = getString(R.string.shopping_cart_price, shoppingCartViewModel.price.toString())
//                binding.priceView.text = String.format(R.string.shopping_cart_price, )
//                println("in observe after submitList")
            }
        }
        binding.checkoutButton.setOnClickListener {
//            shoppingCartAdapter.submitList(shoppingCartViewModel.products.value).also {
//                println("in submitList")
//                println("list to submit: ${shoppingCartViewModel.products.value}")
//            }
//            shoppingCartViewModel.addProduct(Product(69, "test69", "test69 desc", 69.69))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}