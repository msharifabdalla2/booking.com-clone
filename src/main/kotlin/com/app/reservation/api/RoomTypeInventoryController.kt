package com.app.reservation.api

import com.app.reservation.api.dto.InitialiseInventoryRequest
import com.app.reservation.service.InventoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/hotels")
class RoomTypeInventoryController(private val inventoryService: InventoryService) {

    @PostMapping("/{hotelId}/room-types/{roomTypeId}/inventory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun initialiseInventory(
        @Valid @RequestBody request: InitialiseInventoryRequest,
        @PathVariable hotelId: UUID,
        @PathVariable roomTypeId: UUID
    ) {
        inventoryService.initialiseInventory(
            hotelId,
            roomTypeId,
            request.startDate,
            request.endDate
        )
    }
}
