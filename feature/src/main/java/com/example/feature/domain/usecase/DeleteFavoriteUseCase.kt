package com.example.feature.domain.usecase

import com.example.feature.presentation.rvadapter.DataModel

interface DeleteFavoriteUseCase {

    suspend operator fun invoke(dataModel: DataModel)

}