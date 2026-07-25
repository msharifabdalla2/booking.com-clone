package com.app.notification.producer

import com.app.notification.event.ReservationCreatedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ReservationEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, ReservationCreatedEvent>
) {
    fun publishReservationCreated(event: ReservationCreatedEvent) {
        kafkaTemplate.send("reservation-created", event)
    }
}