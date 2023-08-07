package com.example.feature.di

import androidx.lifecycle.ViewModel
import com.example.feature.presentation.favoritefragment.FavoriteFragmentViewModel
import com.example.feature.presentation.mainfragment.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun mainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFragmentViewModel::class)
    fun favoriteFragmentViewModel(viewModel: FavoriteFragmentViewModel): ViewModel


}