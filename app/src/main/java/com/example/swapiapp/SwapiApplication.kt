package com.example.swapiapp

import android.app.Application
import android.content.Context
import com.example.core.network.ImageLoader
import com.example.core.network.NetworkApi
import com.example.feature.di.FeatureDependenciesProvider
import com.ruguide.core_network.di.NetworkComponent
import retrofit2.Retrofit

class SwapiApplication: Application(), FeatureDependenciesProvider {


    companion object{
        private var swapiApplication : SwapiApplication? = null

        fun initialize(context : Context){
            swapiApplication = context.applicationContext as SwapiApplication
        }
    }

    override fun getNetworkApi(): NetworkApi{
        return NetworkComponent.init(this)
    }

    override fun getRetrofit(): Retrofit {
        return getNetworkApi().getRetrofit()
    }

    override fun getImageLoader(): ImageLoader {
        return getNetworkApi().getImageLoader()
    }

}