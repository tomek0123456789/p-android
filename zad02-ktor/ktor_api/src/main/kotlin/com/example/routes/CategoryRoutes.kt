package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.categoryRouting() {
    route("category") {
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
            val category = transaction {
                CategoryEntity.findById(id)?.resultRowToCategory()
            } ?: return@get call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(category)
        }
        get("all") {
            val categories = transaction {
                CategoryEntity.all().map {
                    Category(it.id.value, it.name, it.description, it.iDontKnow, it.whatToPutHere)
                }
            }
            call.respond(categories)
        }
        post("add") {
            val receivedNoIdCategory = call.receive<NoIdCategory>()
            val insertedCategory = transaction {
                CategoryEntity.new {
                    name = receivedNoIdCategory.name
                    description = receivedNoIdCategory.description
                    iDontKnow = receivedNoIdCategory.iDontKnow
                    whatToPutHere = receivedNoIdCategory.whatToPutHere
                }
            }
            call.respond(Category(insertedCategory.id.value, receivedNoIdCategory))
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

            val receivedNoIdCategory = call.receive<NoIdCategory>()

            val insertedCategory = transaction {
                CategoryEntity.findById(id)
            }

            insertedCategory ?: return@put call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            transaction {
                insertedCategory.name = receivedNoIdCategory.name
                insertedCategory.description = receivedNoIdCategory.description
                insertedCategory.iDontKnow = receivedNoIdCategory.iDontKnow
                insertedCategory.whatToPutHere = receivedNoIdCategory.whatToPutHere
            }
            call.respond(insertedCategory.resultRowToCategory())
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
            val category = transaction {
                CategoryEntity.findById(id)
            } ?: return@delete call.respondText(
                "Not found",
                status = HttpStatusCode.NotFound
            )
            transaction { category.delete() }
            call.respond(category.resultRowToCategory())
        }
        //TODO extract the call.parameters["id"] check to another function getting call as a parameter? or an extension
    }

}