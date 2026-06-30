package com.app.auth.domain

import org.springframework.data.repository.ListCrudRepository
import java.util.UUID

interface UserRepository : ListCrudRepository<User, UUID> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}