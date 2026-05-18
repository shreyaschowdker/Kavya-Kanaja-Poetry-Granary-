package com.example.kavyakanaja.backend.routes

import com.example.kavyakanaja.backend.repository.PoetryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.poetryRoutes(repository: PoetryRepository) {
    route("/poems") {
        get {
            val poems = repository.getAllPoems()
            call.respond(HttpStatusCode.OK, poems)
        }
        
        get("/today") {
            val poem = repository.getPoemOfTheDay()
            if (poem != null) {
                call.respond(HttpStatusCode.OK, poem)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "No poem of the day found"))
            }
        }
        
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val poem = repository.getPoemById(id)
            if (poem != null) {
                call.respond(HttpStatusCode.OK, poem)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Poem not found"))
            }
        }
    }

    route("/poets") {
        get {
            val poets = repository.getAllPoets()
            call.respond(HttpStatusCode.OK, poets)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val poet = repository.getPoetById(id)
            if (poet != null) {
                call.respond(HttpStatusCode.OK, poet)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Poet not found"))
            }
        }
    }
}
