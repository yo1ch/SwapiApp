package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Starship
import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class GetStarshipUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetStarshipUseCase {
    override suspend fun invoke(): Result<List<Starship>> = repository.getStarships()
}