package com.app.reservation.domain

import com.app.reservation.domain.ReservationStatus.PENDING
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Table(name = "reservations")
data class Reservation(
    @Id
    val id: UUID? = null,
    val guestId: UUID,
    val hotelId: UUID,
    val roomTypeId: UUID,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val status: ReservationStatus = PENDING,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
