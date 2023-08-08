package com.example.feature.data.remote.dto

data class PlanetsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PlanetDto>
)