package com.app.hotel.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("rooms")
data class Room(
    @Id
    val id: UUID? = null,
    val hotelId: UUID,
    val roomTypeId: UUID,
    val roomNumber: String,
    val floorNumber: Int,
    val isAvailable: Boolean = true
)
