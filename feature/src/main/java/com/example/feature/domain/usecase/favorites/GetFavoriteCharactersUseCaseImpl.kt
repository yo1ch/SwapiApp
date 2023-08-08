package com.example.feature.domain.usecase.favorites

import com.example.feature.domain.repository.SwapiRepository
import com.example.feature.presentation.rvadapter.DataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCharactersUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetFavoriteCharactersUseCase{
    override suspend fun invoke() = repository.getFavoriteCharacters()
}