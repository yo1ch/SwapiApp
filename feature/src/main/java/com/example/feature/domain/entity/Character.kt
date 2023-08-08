package com.example.feature.domain.entity

data class Character(
    val name: String,
    val gender: String,
    val count: Int,
    val films: List<String>,
    val isFavorite: Boolean = false,
    )
