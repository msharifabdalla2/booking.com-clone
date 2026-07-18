package com.app.notification.event

data class ReservationCreatedEvent(
    val reservationId: String,
    val guestEmail: String,
    val hotelName: String,
    val checkInDate: String,
    val checkOutDate: String
)