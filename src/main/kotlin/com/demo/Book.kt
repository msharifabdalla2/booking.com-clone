package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "`Book`")
data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String = "",
    var author: String = "",
)

