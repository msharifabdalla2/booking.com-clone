package com.app.reservation.api

import com.app.reservation.api.dto.CreateReservationRequest
import com.app.reservation.api.dto.ReservationResponse
import com.app.reservation.service.ReservationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ReservationController(private val reservationService: ReservationService) {

    @PostMapping("/hotels/{hotelId}/room-types/{roomTypeId}/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    fun createReservation(
        @PathVariable hotelId: UUID,
        @PathVariable roomTypeId: UUID,
        @Valid @RequestBody request: CreateReservationRequest
    ): ReservationResponse {

        val createdReservation = reservationService.createReservation(
            hotelId,
            roomTypeId,
            request.guestEmail,
            request.guestFirstName,
            request.guestLastName,
            request.checkInDate,
            request.checkOutDate
        )

        return ReservationResponse.from(createdReservation)
    }

    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getReservationById(@PathVariable("id") reservationId: UUID): ReservationResponse {

        val foundReservation = reservationService.getReservationById(reservationId)

        return ReservationResponse.from(foundReservation)
    }

    @PatchMapping("/reservations/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelReservationById(@PathVariable("id") reservationId: UUID) {
        reservationService.cancelReservation(reservationId)
    }



}