package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface GetFavoriteCharactersUseCase {

    suspend operator fun invoke(): Flow<List<Character>>

}