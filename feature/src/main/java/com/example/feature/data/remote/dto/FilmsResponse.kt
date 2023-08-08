package com.example.feature.data.remote.dto

data class FilmsResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<FilmDto>
)