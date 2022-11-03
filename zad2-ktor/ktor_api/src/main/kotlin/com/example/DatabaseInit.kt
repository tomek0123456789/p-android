package com.example

import com.example.models.Categories
import com.example.models.Products
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.databaseInit(): Database {
    val driverClassName = environment.config.property("ktor.database.driverClassName").getString()
    val jdbcUrl = environment.config.property("ktor.database.jdbcUrl").getString()
    val database = Database.connect(jdbcUrl, driverClassName)
    createTables()
    return database
}

private fun createTables() {
    transaction {
        SchemaUtils.create(Products, Categories)
    }
}
