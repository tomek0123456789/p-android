package com.p_android.zad5_shopping.network

import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().build()
    val api: ShoppingApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://63b884b43329392049dea8d1.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ShoppingApi::class.java)
    }
}