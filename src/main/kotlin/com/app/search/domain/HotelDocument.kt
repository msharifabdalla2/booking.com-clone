package com.app.search.domain

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "hotels")
data class HotelDocument(
    @Id
    val id: String,
    val name: String,
    val description: String,
    val city: String,
    val country: String
)
