package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val brand: String,
    val price: Int,
    val category: Category? = null
) : RealmObject()