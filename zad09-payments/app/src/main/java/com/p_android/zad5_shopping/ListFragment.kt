package com.p_android.zad5_shopping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.p_android.zad5_shopping.adapters.ItemListAdapter
import com.p_android.zad5_shopping.data.ProductObject
import com.p_android.zad5_shopping.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(R.layout.fragment_list), RecyclerViewInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        var list: List<ProductObject>
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch (e: IOException) {
                Log.e("DUPA", "IOEx")
                e.printStackTrace()
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("DUPA", "HttpEx")
                e.printStackTrace()
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                Log.e("UDALO SIE", ":)")
                list = response.body()!!
                println("Inside response.isSuccessful")
                list.forEach {
                    println(it.id)
                    println(it.brand)
                    println(it.price)
                    println(it.name)
                    println(it.category)
                }
                println("Closing response.isSuccessful")
                view.adapter = ItemListAdapter(list, this@ListFragment)
            } else {
                Log.e("DUPA", "cos sie wywalilo z responsem, jest body rowne null")
            }
        }
//        println("\nAfter response.isSuccessful")
//        list.forEach {
//            println(it.id)
//            println(it.brand)
//            println(it.price)
//            println(it.name)
//            println(it.category)
//        }
//        view.adapter = ItemListAdapter(list, this)
        return view
    }

    private fun getList(): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 1..10) {
            list.add(Item(i, "title no. $i", "description no. $i"))
        }
        println(list)
        return list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(product: ProductObject) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}