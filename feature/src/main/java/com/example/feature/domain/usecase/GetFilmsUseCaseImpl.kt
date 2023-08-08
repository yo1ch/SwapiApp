package com.example.feature.domain.usecase

import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class GetFilmsUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetFilmsUseCase {
    override suspend fun invoke() = repository.getFilms()
}