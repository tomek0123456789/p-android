package com.p_android.zad5_shopping

import com.p_android.zad5_shopping.data.ProductObject
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingApi {
    @GET("/product/all")
    suspend fun getProducts(): Response<List<ProductObject>>
}