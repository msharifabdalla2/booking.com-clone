package com.app.search.api

import com.app.search.domain.HotelDocument
import com.app.search.service.SearchService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchController(private val searchService: SearchService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchHotels(
        @RequestParam(required = false) city: String?,
        @RequestParam(required = false) name: String?
    ): List<HotelDocument> {

        return searchService.searchHotels(city, name)
    }
}