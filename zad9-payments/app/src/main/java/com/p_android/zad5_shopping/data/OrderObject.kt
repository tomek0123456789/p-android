package com.p_android.zad5_shopping.data

data class Order(
    val id: Int,
    val customerFirstName: String,
    val customerSecondName: String,
    val cart: ShoppingCart?,
) : java.io.Serializable

data class OrderConfirmation(
    val id: Int,
    val confirmed: Boolean
) : java.io.Serializable