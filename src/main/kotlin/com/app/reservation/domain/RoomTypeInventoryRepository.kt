package com.app.reservation.domain

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import java.time.LocalDate
import java.util.UUID

interface RoomTypeInventoryRepository : ListCrudRepository<RoomTypeInventory, UUID> {
    fun existsByRoomTypeIdAndDate(roomTypeId: UUID, date: LocalDate): Boolean
    @Query("SELECT * FROM room_type_inventory WHERE room_type_id = :roomTypeId AND date >= :startDate AND date < :endDate FOR UPDATE")
    fun findInventoryForUpdate(roomTypeId: UUID, startDate: LocalDate, endDate: LocalDate): List<RoomTypeInventory>
}