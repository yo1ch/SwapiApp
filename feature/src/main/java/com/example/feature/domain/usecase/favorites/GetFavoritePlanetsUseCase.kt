package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.entity.Planet
import kotlinx.coroutines.flow.Flow

interface GetFavoritePlanetsUseCase {

    suspend operator fun invoke(): Flow<List<Planet>>

}