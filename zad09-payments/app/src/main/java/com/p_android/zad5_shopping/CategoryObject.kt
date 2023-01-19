package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CategoryObject : RealmObject() {
    @PrimaryKey var id: Int = -1
    var name: String = ""
    var description: String = ""
    var iDontKnow: String = ""
    var whatToPutHere: String = ""
}

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val iDontKnow: String,
    val whatToPutHere: String
)