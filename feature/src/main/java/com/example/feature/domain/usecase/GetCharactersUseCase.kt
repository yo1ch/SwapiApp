package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Character

interface GetCharactersUseCase {

    suspend operator fun invoke(searchQuery: String):Result<List<Character>>

}