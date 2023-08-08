package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Planet

interface GetPlanetUseCase {

    suspend operator fun invoke(searchQuery: String): Result<List<Planet>>

}