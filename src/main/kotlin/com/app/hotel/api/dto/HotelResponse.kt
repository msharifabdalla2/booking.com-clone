package com.app.hotel.api.dto

import com.app.hotel.domain.Hotel
import java.math.BigDecimal
import java.util.UUID

data class HotelResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val address: String,
    val city: String,
    val country: String,
    val latitude: BigDecimal?,
    val longitude: BigDecimal?
) {
    companion object {
        fun from(hotel: Hotel): HotelResponse {
            return HotelResponse(
                id = hotel.id ?: throw IllegalStateException("Saved hotel must have an ID"),
                name = hotel.name,
                description = hotel.description,
                address = hotel.address,
                city = hotel.city,
                country = hotel.country,
                latitude = hotel.latitude,
                longitude = hotel.longitude
            )
        }
    }
}
