package com.app.reservation.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.*

interface ReservationRepository : ListCrudRepository<Reservation, UUID> {
}