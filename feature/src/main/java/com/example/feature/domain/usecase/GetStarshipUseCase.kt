package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Starship

interface GetStarshipUseCase {

    suspend operator fun invoke(): Result<List<Starship>>

}