package com.app.hotel.api

import com.app.hotel.api.dto.CreateHotelRequest
import com.app.hotel.api.dto.CreateRoomTypeRequest
import com.app.hotel.api.dto.HotelResponse
import com.app.hotel.api.dto.RoomTypeResponse
import com.app.hotel.api.dto.UpdateRoomTypeRequest
import com.app.hotel.service.HotelService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/hotels")
class HotelController(private val hotelService: HotelService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createHotel(
        @Valid @RequestBody request: CreateHotelRequest
    ): HotelResponse {

        val createdHotel = hotelService.createHotel(
            request.name,
            request.description,
            request.address,
            request.city,
            request.country,
            request.latitude,
            request.longitude
        )

        return HotelResponse.from(createdHotel)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllHotels(): List<HotelResponse> {

        val hotels = hotelService.getAllHotels()

        return hotels.map { hotel -> HotelResponse.from(hotel) }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getHotelById(@PathVariable id: UUID): HotelResponse {

        val hotel = hotelService.getHotelById(id)

        return HotelResponse.from(hotel)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHotelById(@PathVariable id: UUID) {

        return hotelService.deleteHotelById(id)
    }

    @PostMapping("/{hotelId}/room-types")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoomTypeByHotelId(
        @PathVariable hotelId: UUID,
        @Valid @RequestBody request: CreateRoomTypeRequest
    ): RoomTypeResponse {

        val createdRoomType = hotelService.addRoomTypeByHotelId(
            hotelId,
            request.name,
            request.description,
            request.maxOccupancy,
            request.totalRooms,
            request.amenities
        )

        return RoomTypeResponse.from(createdRoomType)
    }

    @GetMapping("/{hotelId}/room-types")
    @ResponseStatus(HttpStatus.OK)
    fun getRoomTypesByHotelId(@PathVariable hotelId: UUID): List<RoomTypeResponse> {

        val roomTypes = hotelService.getRoomTypesByHotelId(hotelId)

        return roomTypes.map { roomType -> RoomTypeResponse.from(roomType) }
    }

    @GetMapping("/{hotelId}/room-types/{roomTypeId}")
    @ResponseStatus(HttpStatus.OK)
    fun getRoomTypeByHotelIdAndRoomTypeId(
        @PathVariable hotelId: UUID,
        @PathVariable roomTypeId: UUID
    ): RoomTypeResponse {

        val roomType = hotelService.getRoomTypeByHotelIdAndRoomTypeId(hotelId, roomTypeId)

        return RoomTypeResponse.from(roomType)
    }

    @PutMapping("/{hotelId}/room-types/{roomTypeId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateRoomTypeByHotelIdAndRoomTypeId(
        @PathVariable hotelId: UUID,
        @PathVariable roomTypeId: UUID,
        @Valid @RequestBody request: UpdateRoomTypeRequest
    ): RoomTypeResponse {

        val updatedRoom = hotelService.updateRoomTypeByHotelIdAndRoomTypeId(
            hotelId,
            roomTypeId,
            request.name,
            request.description,
            request.maxOccupancy,
            request.totalRooms,
            request.amenities
        )

        return RoomTypeResponse.from(updatedRoom)
    }

    @DeleteMapping("/{hotelId}/room-types/{roomTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRoomTypeByHotelIdAndRoomTypeId(
        @PathVariable hotelId: UUID,
        @PathVariable roomTypeId: UUID
    ) {

        return hotelService.deleteRoomTypeByHotelIdAndRoomTypeId(hotelId, roomTypeId)
    }
}