package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.entity.Starship
import kotlinx.coroutines.flow.Flow

interface GetFavoriteStarshipsUseCase {

    suspend operator fun invoke(): Flow<List<Starship>>

}