package com.example.zad5_shopping_app.data

data class ShoppingCartItem(
    val product: Product,
    private var _quantity: Int
) {
    val quantity: Int get() = _quantity
    fun increaseQuantity() = _quantity++
    fun decreaseQuantity() = _quantity--
    fun canBeDeleted(): Boolean = _quantity == 0
}