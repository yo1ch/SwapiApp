package com.example.feature.di

import com.example.feature.domain.usecase.GetCharactersUseCase
import com.example.feature.domain.usecase.GetCharactersUseCaseImpl
import com.example.feature.domain.usecase.GetPlanetUseCase
import com.example.feature.domain.usecase.GetPlanetUseCaseImpl
import com.example.feature.domain.usecase.GetStarshipUseCase
import com.example.feature.domain.usecase.GetStarshipUseCaseImpl
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
}