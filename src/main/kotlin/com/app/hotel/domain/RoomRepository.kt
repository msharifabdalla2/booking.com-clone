package com.app.hotel.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.UUID

interface RoomRepository : ListCrudRepository<Room, UUID> {
}