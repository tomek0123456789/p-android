package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val iDontKnow: String,
    val whatToPutHere: String
) {
    constructor(id: Int, noIdCategory: NoIdCategory) : this(
        id,
        noIdCategory.name,
        noIdCategory.description,
        noIdCategory.iDontKnow,
        noIdCategory.whatToPutHere
    )
}

@Serializable
data class NoIdCategory(
    val name: String,
    val description: String,
    val iDontKnow: String,
    val whatToPutHere: String
)

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(Categories)

    var name            by Categories.name
    var description     by Categories.description
    var iDontKnow       by Categories.iDontKnow
    var whatToPutHere   by Categories.whatToPutHere

    fun resultRowToCategory() = Category(id.value, name, description, iDontKnow, whatToPutHere)
}

object Categories : IntIdTable("Categories") {
    val name = varchar("name", 50)
    val description = varchar("description", 200)
    val iDontKnow = varchar("iDontKnow", 2137)
    val whatToPutHere = varchar("whatToPutHere", 69420)
}