package com.example.feature.data.remote.dto

data class PeopleResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<CharacterDto>
)