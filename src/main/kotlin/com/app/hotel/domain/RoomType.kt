package com.app.hotel.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("room_types")
data class RoomType(
    @Id
    val id: UUID? = null,
    val hotelId: UUID,
    val name: String,
    val description: String?,
    val maxOccupancy: Int,
    val totalRooms: Int,
    val amenities: String?
)