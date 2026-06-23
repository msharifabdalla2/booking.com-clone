package com.app.reservation.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.UUID

interface GuestRepository : ListCrudRepository<Guest, UUID> {
    fun findByEmail(email: String): Guest?
}