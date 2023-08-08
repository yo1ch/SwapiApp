package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Character
import com.example.feature.domain.repository.SwapiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): GetCharactersUseCase {
    override suspend fun invoke(searchQuery: String): Result<List<Character>> = withContext(
        Dispatchers.IO
    ) {
        return@withContext repository.getCharacters(searchQuery)
    }
}