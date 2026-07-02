package com.app.auth.api

import com.app.auth.api.dto.AuthResponse
import com.app.auth.api.dto.LoginRequest
import com.app.auth.api.dto.RegisterRequest
import com.app.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(
        @Valid @RequestBody userRegisterRequest: RegisterRequest
    ): AuthResponse {

        return authService.register(
            userRegisterRequest.email,
            userRegisterRequest.password,
            userRegisterRequest.role
        )
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun loginUser(
        @Valid @RequestBody userLoginRequest: LoginRequest
    ): AuthResponse {

        return authService.login(
            userLoginRequest.email,
            userLoginRequest.password
        )
    }
}