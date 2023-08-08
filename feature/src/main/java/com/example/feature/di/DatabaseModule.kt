package com.example.feature.di

import android.app.Application
import android.content.Context
import com.example.feature.data.local.FeatureDao
import com.example.feature.data.local.FeatureDatabase
import com.example.feature.domain.repository.SwapiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal class DatabaseModule {

    @Provides
    fun provideFeatureDatabase(
        context: Context
    ): FeatureDao {
        return FeatureDatabase.getInstance(context).featureDao()
    }


}