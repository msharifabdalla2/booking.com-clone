package com.app.hotel.api.dto

import com.app.hotel.domain.RoomType
import java.util.UUID

data class RoomTypeResponse(
    val id: UUID,
    val hotelId: UUID,
    val name: String,
    val description: String?,
    val maxOccupancy: Int,
    val totalRooms: Int,
    val amenities: String?
) {
    companion object {
        fun from(roomType: RoomType): RoomTypeResponse {
            return RoomTypeResponse(
                id = roomType.id ?: throw IllegalStateException("Saved room type must have an ID"),
                hotelId = roomType.hotelId,
                name = roomType.name,
                description = roomType.description,
                maxOccupancy = roomType.maxOccupancy,
                totalRooms = roomType.totalRooms,
                amenities = roomType.amenities
            )
        }
    }
}
