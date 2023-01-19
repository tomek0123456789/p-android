package com.p_android.zad5_shopping.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey val id: Int,
    val firstName: Int,
    val lastName: Int,
): RealmObject()