package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ProductObject : RealmObject(), java.io.Serializable {
    @PrimaryKey var id: Int = -1
    var name: String = ""
    var brand: String = ""
    var price: Int = -1
    var category: CategoryObject? = null
}

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Int,
    val category: Category? = null
) : java.io.Serializable