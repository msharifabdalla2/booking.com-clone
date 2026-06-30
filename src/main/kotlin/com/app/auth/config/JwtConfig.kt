package com.app.auth.config

import com.app.auth.domain.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.Instant

object JwtConfig {

    // TODO: Move to env
    private const val SECRET = ")secret"
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET)

    const val tokenExpiryMs = 86_400_000L

    fun generateToken(user: User): String {
        return JWT.create()
            .withSubject(user.email)
            .withClaim("role", user.role)
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plusMillis(tokenExpiryMs))
            .sign(algorithm)
    }
}