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
            id = hotel.id.toString(),
            name = hotel.name,
            description = hotel.description ?: "",
            city = hotel.city,
            country = hotel.country
        )
        hotelSearchRepository.save(hotelDoc)
    }

    fun searchHotels(city: String?, name: String?): List<HotelDocument> {
        return hotelSearchRepository.findAll().filter { hotel ->
            (city == null || hotel.city.contains(city, ignoreCase = true)) &&
            (name == null || hotel.name.contains(name, ignoreCase = true))
        }
    }
}