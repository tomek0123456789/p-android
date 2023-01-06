package com.p_android.zad5_shopping.network

import com.p_android.zad5_shopping.data.Order
import com.p_android.zad5_shopping.data.OrderConfirmation
import com.p_android.zad5_shopping.data.ProductObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ShoppingApi {
    @GET("/product/all")
    suspend fun getProducts(): Response<List<ProductObject>>

    @Headers("Content-Type: application/json")
    @POST("order")
     fun makeOrder(@Body orderInfo: Order): Call<OrderConfirmation>
}