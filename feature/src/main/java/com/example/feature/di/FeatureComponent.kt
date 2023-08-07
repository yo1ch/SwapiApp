package com.example.feature.di

import android.content.Context
import com.example.feature.presentation.favoritefragment.FavoriteFragment
import com.example.feature.presentation.mainfragment.MainFragment
import dagger.Component

@Component(
    modules = [ViewModelModule::class,],
    dependencies = [FeatureDependenciesProvider::class])
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

    internal abstract fun injectMainFragment(mainFragment : MainFragment)
    internal abstract fun injectFavoriteFragment(favoriteFragment : FavoriteFragment)

}