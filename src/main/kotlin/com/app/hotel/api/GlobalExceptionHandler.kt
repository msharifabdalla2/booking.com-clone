package com.app.hotel.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException) : ResponseEntity<Map<String, String>> {
        // 1. Create a clean error message payload for the client
        val errorBody = mapOf(
            "error" to "Not Found",
            "message" to (ex.message ?: "The requested resource could not be found")
        )

        // 2. Return the payload wrapped in a ResponseEntity with a 404 Status
            return ResponseEntity(errorBody, HttpStatus.NOT_FOUND)
    }
}