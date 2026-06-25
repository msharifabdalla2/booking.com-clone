package com.app.reservation.service

import com.app.reservation.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val guestRepository: GuestRepository,
    private val roomTypeInventoryRepository: RoomTypeInventoryRepository
) {

    @Transactional
    fun createReservation(
        hotelId: UUID,
        roomTypeId: UUID,
        guestEmail: String,
        guestFirstName: String,
        guestLastName: String,
        checkInDate: LocalDate,
        checkOutDate: LocalDate
    ) : Reservation {

        // 1. Validate check in date is before check out date
        if(checkInDate.isAfter(checkOutDate)) {
            throw IllegalArgumentException("Check in date must be before check out date")
        }

        // 2. Find guest by email, create new guest if not found
        val guest = guestRepository.findByEmail(guestEmail) ?: Guest(
            id = null,
            firstName = guestFirstName,
            lastName = guestLastName,
            email = guestEmail
        ).let { guestRepository.save(it) }

        // 3. Lock inventory rows using custom SELECT ... FOR UPDATE query
        val inventoryRowsForUpdate = roomTypeInventoryRepository.findInventoryForUpdate(
            roomTypeId,
            checkInDate,
            checkOutDate
        )

        // 3.5 Ensure inventory is initialised for all requested nights
        val requestedNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate).toInt()
        if (inventoryRowsForUpdate.size != requestedNights) {
            throw IllegalStateException("Inventory not initialised for all requested nights")
        }


        // 4. Verify locked room per room type per day has space / every locked row has space between the requested dates (reserved count <= total count)
        val isAvailable = inventoryRowsForUpdate.all { row ->
            row.reservedCount + 1 <= row.totalCount }

        if (!isAvailable) throw IllegalStateException("No available rooms for the requested dates")

        // 5. Increment reservedCount on each row and save the changes
        inventoryRowsForUpdate.forEach { row ->
            val updatedRow = row.copy(reservedCount = row.reservedCount + 1)
            roomTypeInventoryRepository.save(updatedRow)
        }

        // 6. Create, save and return the final Reservation confirmation record
        val reservation = Reservation(
            id = null,
            guestId = guest.id!!,
            hotelId = hotelId,
            roomTypeId = roomTypeId,
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
        )

        return reservationRepository.save(reservation)
    }
}