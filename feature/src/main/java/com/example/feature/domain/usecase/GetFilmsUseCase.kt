package com.example.feature.domain.usecase

import com.example.feature.domain.entity.Film
import kotlinx.coroutines.flow.Flow


interface GetFilmsUseCase {

    suspend operator fun invoke(): Flow<List<Film>>

}