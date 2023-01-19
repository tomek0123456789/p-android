package com.p_android.zad5_shopping.payments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.p_android.zad5_shopping.data.Order
import com.p_android.zad5_shopping.data.OrderConfirmation
import com.p_android.zad5_shopping.databinding.CheckoutFormBinding
import com.p_android.zad5_shopping.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = CheckoutFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.payButton.setOnClickListener {
            val order = Order(
                id = 3,
                customerFirstName = "testFirstName",
                customerSecondName = "testSecondName",
                cart = null
            )
            makeOrder(order) { result ->
                if (result) {
                    Toast.makeText(this, "Order created", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,
                        "Something went wrong, please try again",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeOrder(order: Order, onResult: (Boolean) -> Unit) {
        val retrofit = RetrofitInstance.api
        GlobalScope.launch(Dispatchers.IO) {
            retrofit.makeOrder(order).enqueue(
                object : Callback<OrderConfirmation> {
                    override fun onResponse(
                        call: Call<OrderConfirmation>, response: Response<OrderConfirmation>,
                    ) {
                        println(response.toString())
                        println("=-====================================")
                        println(call.request().toString())
                        onResult(true)
                    }
                    override fun onFailure(call: Call<OrderConfirmation>, t: Throwable) {
                        onResult(false)
                    }
                }
            )
        }
    }
}