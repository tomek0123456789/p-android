package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserObject : RealmObject() {
    @PrimaryKey var id: Int = -1
    var firstName: Int = -1
    var lastName: Int = -1
}

data class User(
    val id: Int,
    val firstName: Int,
    val lastName: Int,
)