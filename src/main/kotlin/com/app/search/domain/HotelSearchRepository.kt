package com.app.search.domain

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface HotelSearchRepository : ElasticsearchRepository<HotelDocument, String> {
    fun findByCityContainingIgnoreCaseAndNameContainingIgnoreCase(city: String, name: String): List<HotelDocument>
}