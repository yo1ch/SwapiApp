package com.example.feature.domain.usecase

import com.example.feature.domain.repository.SwapiRepository
import com.example.feature.presentation.rvadapter.DataModel
import javax.inject.Inject

class AddFavoriteUseCaseImpl @Inject constructor(
    private val repository: SwapiRepository
): AddFavoriteUseCase {
    override suspend fun invoke(dataModel: DataModel) = repository.addFavorite(dataModel)
}