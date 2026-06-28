package com.app.reservation.api.dto

import com.app.reservation.domain.Reservation
import com.app.reservation.domain.ReservationStatus
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

data class ReservationResponse(
    val id: UUID,
    val guestId: UUID,
    val hotelId: UUID,
    val roomTypeId: UUID,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val status: ReservationStatus,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun from(reservation: Reservation): ReservationResponse {
            return ReservationResponse(
                id = reservation.id ?: throw IllegalStateException("Saved reservation must have an ID"),
                guestId = reservation.guestId,
                hotelId = reservation.hotelId,
                roomTypeId = reservation.roomTypeId,
                checkInDate = reservation.checkInDate,
                checkOutDate = reservation.checkOutDate,
                status = reservation.status,
                createdAt = reservation.createdAt,
                updatedAt = reservation.updatedAt
            )
        }
    }
}
