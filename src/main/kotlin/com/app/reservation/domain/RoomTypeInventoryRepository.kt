package com.app.reservation.domain

import org.springframework.data.repository.ListCrudRepository
import java.time.LocalDate
import java.util.UUID

interface RoomTypeInventoryRepository : ListCrudRepository<RoomTypeInventory, UUID> {
    fun existsByRoomTypeIdAndDate(roomTypeId: UUID, date: LocalDate): Boolean
}