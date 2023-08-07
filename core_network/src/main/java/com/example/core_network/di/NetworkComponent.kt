package com.ruguide.core_network.di

import android.content.Context
import com.example.core.network.NetworkApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ImageLoaderModule::class, RetrofitOkHttpModule::class])
interface NetworkComponent: NetworkApi {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): NetworkComponent
    }

    companion object{
        @Volatile
        private var networkComponent: NetworkComponent? = null
        @Synchronized
        fun init(context: Context): NetworkComponent {
            if (networkComponent == null){
                networkComponent = DaggerNetworkComponent
                    .builder()
                    .context(context.applicationContext)
                    .build()
            }
            return networkComponent!!
        }
    }
}