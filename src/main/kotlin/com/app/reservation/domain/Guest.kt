package com.app.reservation.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "guests")
data class Guest(
    @Id
    val id: UUID? = null,
    val firstName: String,
    val lastName: String,
    val email: String
)
