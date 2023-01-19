package com.example.models

import com.example.models.CategoryEntity.Companion.referrersOn
import com.example.models.Products.references
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Product(val id: Int, val name: String, val brand: String, val price: Int, val categoryId: Int) {
    constructor(id: Int, noIdProduct: NoIdProduct) : this(
        id,
        noIdProduct.name,
        noIdProduct.brand,
        noIdProduct.price,
        noIdProduct.categoryId
    )
}

@Serializable
data class NoIdProduct(val name: String, val brand: String, val price: Int, val categoryId: Int)

class ProductEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductEntity>(Products)

    var name        by Products.name
    var brand       by Products.brand
    var price       by Products.price
    var categoryId  by Products.categoryId

    fun resultRowToProduct() =
        Product(
            id = id.value,
            name = name,
            brand = brand,
            price = price,
            categoryId = categoryId
        )
}

object Products : IntIdTable("Products") {
    val name = varchar("name", 50)
    val brand = varchar("brand", 50)
    val price = integer("price")
    val categoryId = integer("categoryId").references(Categories.id)
}