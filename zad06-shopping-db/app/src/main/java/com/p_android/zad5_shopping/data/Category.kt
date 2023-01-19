package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val iDontKnow: String,
    val whatToPutHere: String
) : RealmObject()