package com.app.reservation.service

import com.app.reservation.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
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

        if(checkInDate.isAfter(checkOutDate)) {
            throw IllegalArgumentException("Check in date must be before check out date")
        }

        val guest = guestRepository.findByEmail(guestEmail) ?: Guest(
            id = null,
            firstName = guestFirstName,
            lastName = guestLastName,
            email = guestEmail
        ).let { guestRepository.save(it) }

        val inventoryForUpdate = roomTypeInventoryRepository.findInventoryForUpdate(
            roomTypeId,
            checkInDate,
            checkOutDate
        ) {

        }







    }
}