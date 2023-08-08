package com.example.feature.domain.usecase

import com.example.feature.presentation.rvadapter.DataModel

interface AddFavoriteUseCase {

    suspend operator fun invoke(dataModel: DataModel)

}