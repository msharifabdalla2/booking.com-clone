package com.app.search.service

import com.app.hotel.domain.Hotel
import com.app.search.domain.HotelDocument
import com.app.search.domain.HotelSearchRepository
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val hotelSearchRepository: HotelSearchRepository
) {
    fun indexHotel(hotel: Hotel) {
        val hotelDoc: HotelDocument = HotelDocument(
            id = hotel.id?.toString()
                ?: throw IllegalArgumentException("Hotel ID cannot be null for indexing"),
            name = hotel.name,
            description = hotel.description ?: "",
            city = hotel.city,
            country = hotel.country
        )
        hotelSearchRepository.save(hotelDoc)
    }

    fun searchHotels(city: String?, name: String?): List<HotelDocument> {

        if (city.isNullOrBlank() && name.isNullOrBlank()) {
            return hotelSearchRepository.findAll().toList()
        }

        return hotelSearchRepository.findByCityContainingIgnoreCaseAndNameContainingIgnoreCase(
            city ?: "",
            name ?: ""
        )
    }
}