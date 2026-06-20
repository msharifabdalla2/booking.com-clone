package com.app.reservation.api.dto

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class InitialiseInventoryRequest(
    @field:NotNull(message = "Start date is required")
    @field:FutureOrPresent(message = "Start date must be today or in the future")
    val startDate: LocalDate,

    @field:NotNull(message = "End date is required")
    @field:FutureOrPresent(message = "End date must be today or in the future")
    val endDate: LocalDate
)
