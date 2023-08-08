package com.example.feature.di

import com.example.feature.domain.usecase.AddFavoriteUseCase
import com.example.feature.domain.usecase.AddFavoriteUseCaseImpl
import com.example.feature.domain.usecase.DeleteFavoriteUseCaseImpl
import com.example.feature.domain.usecase.DeleteFavoriteUseCase
import com.example.feature.domain.usecase.GetCharactersUseCase
import com.example.feature.domain.usecase.GetCharactersUseCaseImpl
import com.example.feature.domain.usecase.GetPlanetUseCase
import com.example.feature.domain.usecase.GetPlanetUseCaseImpl
import com.example.feature.domain.usecase.GetStarshipUseCase
import com.example.feature.domain.usecase.GetStarshipUseCaseImpl
import com.example.feature.domain.usecase.SaveFilmsUseCase
import com.example.feature.domain.usecase.SaveFilmsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
internal abstract class UseCaseModule {
    @Binds
    abstract fun provideGetCharactersUseCase(
        getCharactersUseCase: GetCharactersUseCaseImpl
    ): GetCharactersUseCase

    @Binds
    abstract fun provideGetPlanetsUseCase(
        getPlanetUseCase: GetPlanetUseCaseImpl
    ): GetPlanetUseCase

    @Binds
    abstract fun provideGetStarshipsUseCase(
        getStarshipUseCase: GetStarshipUseCaseImpl
    ): GetStarshipUseCase

    @Binds
    abstract fun provideSaveFilmsUseCase(
        saveFilmsUseCase: SaveFilmsUseCaseImpl
    ): SaveFilmsUseCase

    @Binds
    abstract fun provideAddFavoriteUseCase(
        addFavoriteUseCase: AddFavoriteUseCaseImpl
    ): AddFavoriteUseCase

    @Binds
    abstract fun provideDeleteFavoriteUseCase(
        deleteFavoriteUseCase: DeleteFavoriteUseCaseImpl
    ): DeleteFavoriteUseCase
}