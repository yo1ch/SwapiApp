package com.example.feature.di

import com.example.core.network.ImageLoader
import com.example.core.network.NetworkApi
import retrofit2.Retrofit

interface FeatureDependenciesProvider {

    fun getNetworkApi(): NetworkApi

    fun getRetrofit(): Retrofit

    fun getImageLoader(): ImageLoader

}