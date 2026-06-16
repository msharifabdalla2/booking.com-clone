package com.app.hotel.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.UUID

interface RoomTypeRepository : ListCrudRepository<RoomType, UUID> {
    fun findHotelById(hotelId: UUID): RoomType?
    fun findByHotelsId(hotelId: UUID): List<RoomType>
    fun findByIdAndHotelId(id: UUID, hotelId: UUID): RoomType?
}