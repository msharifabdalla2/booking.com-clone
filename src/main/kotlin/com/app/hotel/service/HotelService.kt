package com.app.hotel.service

import com.app.hotel.domain.Hotel
import com.app.hotel.domain.HotelRepository
import com.app.hotel.domain.RoomType
import com.app.hotel.domain.RoomTypeRepository
import com.app.search.service.SearchService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class HotelService(
    private val hotelRepository: HotelRepository,
    private val roomTypeRepository: RoomTypeRepository,
    private val searchService: SearchService
) {
    fun createHotel(name: String,
                    description: String?,
                    address: String,
                    city: String,
                    country: String,
                    latitude: BigDecimal?,
                    longitude: BigDecimal?): Hotel {
        val newHotel = Hotel(
            id = null,
            name = name,
            description = description,
            address = address,
            city = city,
            country = country,
            latitude = latitude,
            longitude = longitude
        )

        val savedHotel = hotelRepository.save(newHotel)
        searchService.indexHotel(savedHotel)
        return savedHotel
    }

    fun getHotelById(id: UUID): Hotel {
         return hotelRepository.findByIdOrNull(id)
             ?: throw NoSuchElementException("Hotel not found: $id")
    }

    fun getAllHotels(): List<Hotel> {
        return hotelRepository.findAll()
            ?: throw NoSuchElementException("No hotels found")
    }

    fun deleteHotelById(id: UUID) : Unit {

        getHotelById(id)
        return hotelRepository.deleteById(id)
    }

    fun addRoomTypeByHotelId(hotelId: UUID,
                    name: String,
                    description: String?,
                    maxOccupancy: Int,
                    totalRooms: Int,
                    amenities: String?) : RoomType {

        val newRoomType = RoomType(
            id = null,
            hotelId = hotelId,
            name = name,
            description = description,
            maxOccupancy = maxOccupancy,
            totalRooms = totalRooms,
            amenities = amenities
        )

        return roomTypeRepository.save(newRoomType)
    }

    fun getRoomTypesByHotelId(hotelId: UUID) : List<RoomType> {
        return roomTypeRepository.findByHotelId(hotelId)
    }

    fun getRoomTypeByHotelIdAndRoomTypeId(hotelId: UUID, roomTypeId: UUID) : RoomType {
        return roomTypeRepository.findByIdAndHotelId(roomTypeId, hotelId)
            ?: throw NoSuchElementException("Room type not found for hotel: $hotelId")
    }

    fun updateRoomTypeByHotelIdAndRoomTypeId(
        hotelId: UUID,
        roomTypeId: UUID,
        name: String,
        description: String?,
        maxOccupancy: Int,
        totalRooms: Int,
        amenities: String?
    ) : RoomType {
        val existingRoomType = roomTypeRepository.findByIdAndHotelId(roomTypeId, hotelId)
            ?: throw NoSuchElementException("Room type not found")

        val updatedRoomType = existingRoomType.copy(
            name = name,
            description = description,
            maxOccupancy = maxOccupancy,
            totalRooms = totalRooms,
            amenities = amenities
        )

        return roomTypeRepository.save(updatedRoomType)
    }

    fun deleteRoomTypeByHotelIdAndRoomTypeId(hotelId: UUID, roomTypeId: UUID) : Unit {
        val existingRoomType = roomTypeRepository.findByIdAndHotelId(roomTypeId, hotelId)
            ?: throw NoSuchElementException("Room type not found")

        return roomTypeRepository.deleteById(roomTypeId)
    }

}