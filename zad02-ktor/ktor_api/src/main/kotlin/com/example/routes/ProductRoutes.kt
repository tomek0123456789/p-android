package com.example.routes

import com.example.models.NoIdProduct
import com.example.models.Product
import com.example.models.ProductEntity
import com.example.models.Products
import com.example.models.Products.name
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.productRouting() {
    route("product") {
        get("{id?}") {
            val id = try {
                call.parameters.getOrFail<Int>("id")
            } catch (e: MissingRequestParameterException) {
                println("ERROR: NO PARAMETER")
                return@get call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            } catch (e: ParameterConversionException) {
                println("ERROR: INVALID PARAMETER")
                return@get call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            }
            val product = transaction {
                ProductEntity.findById(id)?.resultRowToProduct()
            } ?: return@get call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(product)
        }
        get("all") {
            val products = transaction {
                ProductEntity.all().map {
                    Product(it.id.value, it.name, it.brand, it.price, it.categoryId)
                }
            }
            call.respond(products)
        }
        post("add") {
            val receivedNoIdProduct = call.receive<NoIdProduct>()
            val insertedProduct = transaction {
                ProductEntity.new {
                    name = receivedNoIdProduct.name
                    brand = receivedNoIdProduct.brand
                    price = receivedNoIdProduct.price
                    categoryId = receivedNoIdProduct.categoryId
                }
            }
            call.respond(Product(insertedProduct.id.value, receivedNoIdProduct))
        }
        put("{id?}") {
            val id = try {
                call.parameters.getOrFail<Int>("id")
            } catch (e: MissingRequestParameterException) {
                println("ERROR: NO PARAMETER")
                return@put call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            } catch (e: ParameterConversionException) {
                println("ERROR: INVALID PARAMETER")
                return@put call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            }

            val receivedNoIdProduct = call.receive<NoIdProduct>()

            val insertedProduct = transaction {
                ProductEntity.findById(id)
            }

            insertedProduct ?: return@put call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            transaction {
                insertedProduct.name = receivedNoIdProduct.name
                insertedProduct.brand = receivedNoIdProduct.brand
                insertedProduct.price = receivedNoIdProduct.price
                insertedProduct.categoryId = receivedNoIdProduct.categoryId
            }
            call.respond(insertedProduct.resultRowToProduct())
        }
        delete("{id}") {
            val id = try {
                call.parameters.getOrFail<Int>("id")
            } catch (e: MissingRequestParameterException) {
                println("ERROR: NO PARAMETER")
                return@delete call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            } catch (e: ParameterConversionException) {
                println("ERROR: INVALID PARAMETER")
                return@delete call.respondText("BAD REQUEST", status = HttpStatusCode.BadRequest)
            }
            val product = transaction {
                ProductEntity.findById(id)
            } ?: return@delete call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            transaction { product.delete() }
            call.respond(product.resultRowToProduct())
        }
        //TODO extract the call.parameters["id"] check to another function getting call as a parameter? or an extension
    }
}