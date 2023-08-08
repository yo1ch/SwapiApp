package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Planet
import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class GetPlanetUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetPlanetUseCase {
    override suspend fun invoke(): Result<List<Planet>> = repository.getPlanets()
}