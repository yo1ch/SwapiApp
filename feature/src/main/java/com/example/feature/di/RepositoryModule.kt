package com.example.feature.di

import com.example.feature.data.remote.RetrofitRepository
import com.example.feature.domain.repository.SwapiRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun provideSwapiRepository(
        repository: RetrofitRepository
    ): SwapiRepository

}