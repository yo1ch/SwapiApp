package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class GetFavoriteStarshipsUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetFavoriteStarshipsUseCase {
    override suspend fun invoke() = repository.getFavoriteStarships()
}