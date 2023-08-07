package com.ruguide.core_network.di

import android.content.Context
import com.example.core.network.ImageLoader
import com.ruguide.core_network.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {
    @Singleton
    @Provides fun provideImageLoader( context: Context) : ImageLoader = GlideImageLoader(context)
}