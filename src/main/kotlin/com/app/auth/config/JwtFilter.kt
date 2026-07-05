package com.app.auth.config

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 1. Read the "Authorization" header
        val authHeader = request.getHeader("Authorization")

        // 2. If missing or does not start with "Bearer ", pass it down the chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        // 3. Extract the clean token string
        val token = authHeader.removePrefix("Bearer ")

        try {
            // 4. Verify the token using JwtConfig algo
            val verifier = JWT.require(JwtConfig.algorithm).build()
            val decoded = verifier.verify(token)

            // 5. Extract the identify details
            val email = decoded.subject
            val role = decoded.getClaim("role").asString()

            // 6. Build Spring's authentication ticket and save it to the context
            val auth = UsernamePasswordAuthenticationToken(
                email,
                null,
                listOf(SimpleGrantedAuthority("ROLE_$role"))
            )
            SecurityContextHolder.getContext().authentication = auth
        } catch (e: JWTVerificationException) {
            // 7. If invalid, catch the exception and do nothing
            // Spring Security will reject the request
        }

        filterChain.doFilter(request, response)
    }
}