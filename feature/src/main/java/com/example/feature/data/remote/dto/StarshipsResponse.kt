package com.example.feature.data.remote.dto

data class StarshipsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<StarshipDto>
)