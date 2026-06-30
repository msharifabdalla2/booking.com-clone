package com.app.auth.api.dto

data class AuthResponse(
    val accessToken: String,
    val email: String,
    val role: String,
    val expiresIn: Long
)
