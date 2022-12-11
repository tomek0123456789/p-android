package com.p_android.zad5_shopping.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ShoppingCart(
    @PrimaryKey val id: Int,
    @PrimaryKey val user: User,
    val createdOn: String,
    val productList: RealmList<Product>
) : RealmObject()