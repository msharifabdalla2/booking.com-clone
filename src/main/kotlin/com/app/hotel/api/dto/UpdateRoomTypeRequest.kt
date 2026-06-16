package com.app.hotel.api.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class UpdateRoomTypeRequest(
    @field:NotBlank(message = "Room type name is required")
    val name: String,

    val description: String?,

    @field:Min(value = 1, message = "Maximum occupancy must be at least 1")
    val maxOccupancy: Int,

    @field:Min(value = 1, message = "Total rooms must be at least 1")
    val totalRooms: Int,

    val amenities: String?
)
