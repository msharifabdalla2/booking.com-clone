package com.app.notification.consumer

import com.app.notification.event.ReservationCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ReservationEventConsumer {
  
  private val log = LoggerFactory.getLogger(ReservationEventConsumer::class.java)

  @KafkaListener(topics = ["reservation-created"], groupId = "booking-service")
  fun consume(event: ReservationCreatedEvent) {
    log.info("Received reservation created event: $event")
  }
}