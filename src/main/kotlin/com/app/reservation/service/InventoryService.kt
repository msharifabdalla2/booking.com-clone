package com.app.reservation.service

import com.app.hotel.service.HotelService
import com.app.reservation.domain.RoomTypeInventory
import com.app.reservation.domain.RoomTypeInventoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import java.time.LocalDate

@Service
class InventoryService(
    private val roomTypeInventoryRepository: RoomTypeInventoryRepository,
    private val hotelService: HotelService
) {

    @Transactional
    fun initialiseInventory(
        hotelId: UUID,
        roomTypeId: UUID,
        startDate: LocalDate,
        endDate: LocalDate
    ) {

        // 1. Validate the hotel exists
        hotelService.getHotelById(hotelId)

        // 2. Validate the room type exists and belongs to that hotel
        val roomType = hotelService.getRoomTypeByHotelIdAndRoomTypeId(hotelId, roomTypeId)

        // 3. Validation safeguard: Ensure the date range makes physical sense
        if (!startDate.isBefore(endDate)) {
            throw IllegalArgumentException("Start date must be before end date")
        }

        // 4. Loop from startDate up until (but excluding) endDate
        var currentDate = startDate
        while (currentDate.isBefore(endDate)) {

            // 5. Idempotency check
            if (!roomTypeInventoryRepository.existsByRoomTypeIdAndDate(roomTypeId, currentDate)) {
                val inventoryRow = RoomTypeInventory(
                    id = null,
                    hotelId = hotelId,
                    roomTypeId = roomTypeId,
                    date = currentDate,
                    totalCount = roomType.totalRooms
                )
                roomTypeInventoryRepository.save(inventoryRow)
            }

            currentDate = currentDate.plusDays(1)
        }

    }
}
