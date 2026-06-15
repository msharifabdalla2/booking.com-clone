package com.app.hotel.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("hotels")
data class Hotel(
    @Id
    val id: UUID? = null,
    val name: String,
    val description: String?,
    val address: String,
    val city: String,
    val country: String,
    val latitude: BigDecimal?,
    val longitude: BigDecimal?
)