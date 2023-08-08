package com.example.feature.domain.usecase

import com.example.feature.domain.repository.SwapiRepository
import javax.inject.Inject

class SaveFilmsUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): SaveFilmsUseCase {
    override suspend fun invoke(): Result<Unit> = repository.saveFilms()
}