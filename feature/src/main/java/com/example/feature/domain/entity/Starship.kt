package com.example.feature.domain.entity

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
    val isFavorite: Boolean = false,
)