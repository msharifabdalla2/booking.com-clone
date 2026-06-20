package com.app.reservation.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "room_type_inventory")
data class RoomTypeInventory(
    @Id
    val id: UUID? = null,
    val hotelId: UUID,
    val roomTypeId: UUID,
    val date: java.time.LocalDate,
    val totalCount: Int,
    val reservedCount: Int = 0
)
