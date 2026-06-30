package com.app.auth.service

import com.app.auth.api.dto.AuthResponse
import com.app.auth.config.JwtConfig
import com.app.auth.domain.User
import com.app.auth.domain.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    fun register(
        email: String,
        password: String,
        role: String
    ): AuthResponse {

        // 1. Check if email exists
        if(userRepository.existsByEmail(email)) throw IllegalArgumentException("Email address '$email' already exists")

        // 2. Hash password with BCrypt
        val hashedPassword = passwordEncoder.encode(password)!!

        // 3. Create and save new user
        val newUser = User(
            id = null,
            email = email,
            passwordHash = hashedPassword,
            role = role
        ).let { userRepository.save(it) }

        // 4. Generate and return JWT
        val token = JwtConfig.generateToken(newUser)

        return AuthResponse(
            accessToken = token,
            email = newUser.email,
            role = newUser.role,
            expiresIn = JwtConfig.tokenExpiryMs / 1000
        )
    }

    fun login(email: String, password: String): AuthResponse {

        // 1. Find user by email
        val foundUser = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Invalid credentials, User with '$email' not found")

        // 2. Check password
        if(!passwordEncoder.matches(password, foundUser.passwordHash)) throw IllegalArgumentException("Invalid password")

        // 3. Generate and return JWT
        val token = JwtConfig.generateToken(foundUser)

        return AuthResponse(
            accessToken = token,
            email = foundUser.email,
            role = foundUser.role,
            expiresIn = JwtConfig.tokenExpiryMs / 1000
        )
    }
}