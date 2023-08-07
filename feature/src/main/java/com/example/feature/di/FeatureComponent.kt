package com.example.feature.di

import android.content.Context
import dagger.Component

@Component(dependencies = [FeatureDependenciesProvider::class,])
abstract class FeatureComponent {
    @Component.Builder
    interface Builder{
        fun featureDependenciesProvider(
            featureDependenciesProvider: FeatureDependenciesProvider
        ): Builder

        fun build(): FeatureComponent
    }

    companion object{
        @Volatile
        private var featureComponent : FeatureComponent? = null
        @Synchronized
        fun init(context: Context): FeatureComponent{
            if (featureComponent == null){
                val deps = context.applicationContext as FeatureDependenciesProvider
                featureComponent = DaggerFeatureComponent
                    .builder()
                    .featureDependenciesProvider(deps)
                    .build()
            }
            return featureComponent!!
        }
    }

}