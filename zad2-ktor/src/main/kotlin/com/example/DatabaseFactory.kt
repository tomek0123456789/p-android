package com.example

import com.example.models.Categories
import com.example.models.Products
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/*  which way is better?
object DatabaseFactory {

    fun init() {
        val driverClassName = "org.sqlite.JDBC"
        val jdbcURL = "jdbc:sqlite:src/main/resources/data.db"
        val database = Database.connect(jdbcURL, driverClassName)
    }

    fun createTables() {
        transaction {
            SchemaUtils.create(Products, Categories)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
*/

