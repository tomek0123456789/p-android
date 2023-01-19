package com.example.zad5_shopping_app.ui.shopping_cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zad5_shopping_app.data.Product
import com.example.zad5_shopping_app.data.ShoppingCartItem

class ShoppingCartViewModel : ViewModel() {
    private val _products = MutableLiveData<List<ShoppingCartItem>>(emptyList())
    val products: LiveData<List<ShoppingCartItem>> = _products

    private val _price = MutableLiveData<Double>(0.0)
    val price: Double get() = _price.value!!
    //todo change textView in shopping cart list to bindingAdapter so as to display price

//    init {
//        _price.value = 0.0
//        _products.value = emptyList()
//    }

    fun addProduct(product: Product) {
        println("in addProduct")
        val list = products.value!!.toMutableList()
        for (cartItem in list) {
            if (cartItem.product == product) {
                cartItem.increaseQuantity()
                increasePrice(cartItem.product.price)
                _products.value = list
                println(products.value)
                return
            }
        }
        val cartItem = ShoppingCartItem(product, 1)
        list.add(cartItem)
        increasePrice(cartItem.product.price)
        _products.value = list
        println(products.value)
        println(price)
    }

    fun removeProduct(product: Product) {
        println("in removeProduct")
        val list = products.value!!.toMutableList()
        for (cartItem in list) {
            if (cartItem.product == product) {
                decreasePrice(cartItem.product.price)
                cartItem.decreaseQuantity()
                if (cartItem.canBeDeleted()) {
                    list.remove(cartItem)
                }
                _products.value = list
                break
            }
        }
        println(products.value)
        println(price)
    }

    fun getCartItemFromLiveData(shoppingCartItem: ShoppingCartItem): ShoppingCartItem =
        products.value?.find { it.product == shoppingCartItem.product }!!

    fun getProductQuantity(product: Product): Int =
        products.value?.find { it.product.id == product.id }?.quantity!!

    private fun decreasePrice(amount: Double) {
        val newPrice = _price.value!! - amount
        _price.value = newPrice
    }
    private fun increasePrice(amount: Double) {
        val newPrice = _price.value!! + amount
       _price.value = newPrice
    }
}