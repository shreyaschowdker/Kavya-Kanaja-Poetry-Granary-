package com.example.kavyakanaja.backend.plugins

import com.example.kavyakanaja.backend.routes.*
import com.example.kavyakanaja.backend.repository.PoetryRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = PoetryRepositoryImpl()
    
    routing {
        get("/") {
            call.respondText("Welcome to Kavya-Kanaja API!")
        }
        poetryRoutes(repository)
    }
}
