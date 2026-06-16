package com.app.hotel.api.dto

import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateHotelRequest(
    @field:NotBlank(message = "Hotel name is required")
    val name: String,

    val description: String?,

    @field:NotBlank(message = "Address is required")
    val address: String,

    @field:NotBlank(message = "City is required")
    val city: String,

    @field:NotBlank(message = "Country is required")
    val country: String,

    val latitude: BigDecimal?,
    val longitude: BigDecimal?,
) {
}