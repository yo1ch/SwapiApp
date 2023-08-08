package com.example.feature.domain.usecase

interface SaveFilmsUseCase {

    suspend operator fun invoke(): Result<Unit>

}