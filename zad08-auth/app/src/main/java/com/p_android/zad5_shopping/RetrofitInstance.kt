package com.p_android.zad5_shopping

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    @OptIn(ExperimentalSerializationApi::class)
    val api: ShoppingApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://fe79-31-182-206-174.eu.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShoppingApi::class.java)
    }
}