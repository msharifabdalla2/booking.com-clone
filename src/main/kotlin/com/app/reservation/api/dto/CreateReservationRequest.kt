package com.app.reservation.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class CreateReservationRequest(
    @field:NotBlank(message = "Guest email is required")
    @field:Email(message = "Guest email must be a valid email address")
    val guestEmail: String,

    @field:NotBlank(message = "Guest first name is required")
    val guestFirstName: String,

    @field:NotBlank(message = "Guest last name is required")
    val guestLastName: String,

    @field:NotNull(message = "Check in date is required")
    @field:FutureOrPresent(message = "Check in date must be today or in the future")
    val checkInDate: LocalDate,

    @field:NotNull(message = "Check out date is required")
    @field:Future(message = "Check out date must be today or in the future")
    val checkOutDate: LocalDate
)
