package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class GetFavoritePlanetsUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetFavoritePlanetsUseCase {
    override suspend fun invoke() = repository.getFavoritePlanets()
}