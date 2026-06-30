package com.app.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table(name = "users")
data class User(
    @Id
    val id: UUID? = null,
    val email: String,
    val passwordHash: String,
    val role: String,
    val createdAt: Instant = Instant.now()
)
