package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Planet

interface GetPlanetUseCase {

    suspend operator fun invoke(): Result<List<Planet>>

}