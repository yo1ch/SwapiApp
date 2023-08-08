package com.example.feature.domain.usecase

import com.example.feature.domain.repository.SwapiRepository
import com.example.feature.presentation.rvadapter.DataModel
import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): DeleteFavoriteUseCase {
    override suspend fun invoke(dataModel: DataModel) = repository.deleteFavorite(dataModel)
}