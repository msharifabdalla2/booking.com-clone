package com.demo

import org.springframework.data.repository.CrudRepository;
import java.awt.print.Book


interface BookRepository : CrudRepository<Book, Long> {
}