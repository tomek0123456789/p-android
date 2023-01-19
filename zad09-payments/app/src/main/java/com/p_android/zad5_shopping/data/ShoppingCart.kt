package com.p_android.zad5_shopping.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ShoppingCartObject : RealmObject() {
    @PrimaryKey var id: Int = -1
    var user: UserObject? = null
    var createdOn: String = ""
    var productList: RealmList<ProductObject> = RealmList()
}

data class ShoppingCart(
    val id: Int,
    val user: User,
    val createdOn: String,
    val productList: List<Product>
)