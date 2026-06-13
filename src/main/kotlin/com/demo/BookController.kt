package com.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.awt.print.Book

@RestController
@RequestMapping("/books")
class BookController(private val bookRepository: BookRepository) {
    @PostMapping
    fun createBook(@RequestBody book: Book): Book {
        return bookRepository.save(book)
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Long): Book {
        return bookRepository.findById(id)
            .orElseThrow { RuntimeException("Book not found") }
    }
}