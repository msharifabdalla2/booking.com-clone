package com.app.hotel.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.Optional
import java.util.UUID

interface RoomTypeRepository : ListCrudRepository<RoomType, UUID> {
    fun findByHotelId(hotelId: UUID): List<RoomType>
    fun findByIdAndHotelId(id: UUID, hotelId: UUID): RoomType?
}