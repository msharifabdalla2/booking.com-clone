package com.app.common.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        // 1. Create a mutable map to collect all field-specific errors
        val errors = mutableMapOf<String, String>()

        // 2. Loop through all the field errors caught by Spring
        ex.bindingResult.fieldErrors.forEach { fieldError ->
            val fieldName = fieldError.field
            val errorMessage = fieldError.defaultMessage ?: "Invalid value"

            // Example output: "name" to "Hotel name cannot be blank"
            errors[fieldName] = errorMessage
        }

        // 3. Return the map of errors with a clean 400 Bad Request status
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleUnreadable(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> {
        val errorBody = mapOf(
            "error" to "Bad Request",
            "message" to "Request body is missing, or malformed"
        )

        return ResponseEntity(errorBody, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val errorBody = mapOf(
            "error" to "Bad Request",
            "message" to (ex.message ?: "Invalid request")
        )

        return ResponseEntity(errorBody, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(ex: IllegalStateException): ResponseEntity<Map<String, String>> {
        val errorBody = mapOf(
            "error" to "Conflict",
            "message" to (ex.message ?: "Operation not allowed")
        )

        return ResponseEntity(errorBody, HttpStatus.CONFLICT)
    }
}