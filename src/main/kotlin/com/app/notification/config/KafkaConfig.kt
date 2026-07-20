package com.app.notification.config

import com.app.notification.event.ReservationCreatedEvent
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, ReservationCreatedEvent> {
        val config = mapOf<String, Any>(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, ReservationCreatedEvent> {
        return KafkaTemplate(producerFactory())
    }
}